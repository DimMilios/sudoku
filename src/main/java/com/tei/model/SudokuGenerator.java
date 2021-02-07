package com.tei.model;

import java.util.Arrays;

import static com.tei.model.SudokuConstants.*;

public class SudokuGenerator {
	private int[][] generatedBoard;
	private int[][] solvedCopy;

	private final int size = BOARD_SIZE;
	private int sizeSqrt;
	private int missingDigits = 40;

	private static SudokuGenerator instance = null;

	private SudokuGenerator() {
	}

	public void init() {
		// Compute square root of N
		Double SRNd = Math.sqrt(size);
		sizeSqrt = SRNd.intValue();

		generatedBoard = new int[size][size];
		solvedCopy = new int[size][size];

		fillValues();
	}

	public void initWithMissingDigits(int missingDigits) {
		this.missingDigits = missingDigits;
		init();
	}

	public static SudokuGenerator getInstance() {
		if (instance == null) {
			instance = new SudokuGenerator();
		}
		return instance;
	}

	// SudokuGenerator Generator
	public void fillValues() {
		// Fill the diagonal of SRN x SRN matrices
		fillDiagonal();

		// Fill remaining blocks
		fillRemaining(0, sizeSqrt);

		// Keep a copy of the solution
		for (int i = 0; i < generatedBoard.length; i++) {
			solvedCopy[i] = Arrays.copyOf(generatedBoard[i], generatedBoard[i].length);
		}

		// Remove Randomly K digits to make game
		removeKDigits();
	}

	// Fill the diagonal SRN number of SRN x SRN matrices
	void fillDiagonal() {
		for (int i = 0; i < size; i = i + sizeSqrt)
			// for diagonal box, start coordinates->i==j
			fillBox(i, i);
	}

	// Returns false if given 3 x 3 block contains num.
	boolean unUsedInBox(int rowStart, int colStart, int num) {
		for (int i = 0; i < sizeSqrt; i++)
			for (int j = 0; j < sizeSqrt; j++)
				if (generatedBoard[rowStart + i][colStart + j] == num)
					return false;

		return true;
	}

	// Fill a 3 x 3 matrix.
	void fillBox(int row, int col) {
		int num;
		for (int i = 0; i < sizeSqrt; i++) {
			for (int j = 0; j < sizeSqrt; j++) {
				do {
					num = randomGenerator(size);
				}
				while (!unUsedInBox(row, col, num));

				generatedBoard[row + i][col + j] = num;
			}
		}
	}

	// Random generator
	int randomGenerator(int num) {
		return (int) Math.floor((Math.random() * num + 1));
	}

	// A recursive function to fill remaining
	// matrix
	boolean fillRemaining(int i, int j) {
		//  System.out.println(i+" "+j);
		if (j >= size && i < size - 1) {
			i = i + 1;
			j = 0;
		}
		if (i >= size && j >= size)
			return true;

		if (i < sizeSqrt) {
			if (j < sizeSqrt)
				j = sizeSqrt;
		} else if (i < size - sizeSqrt) {
			if (j == (i / sizeSqrt) * sizeSqrt)
				j = j + sizeSqrt;
		} else {
			if (j == size - sizeSqrt) {
				i = i + 1;
				j = 0;
				if (i >= size)
					return true;
			}
		}

		for (int num = 1; num <= size; num++) {
			if (SudokuValidator.isSafe(generatedBoard, i, j, num))
//            if (CheckIfSafe(i, j, num))
			{
				generatedBoard[i][j] = num;
				if (fillRemaining(i, j + 1))
					return true;

				generatedBoard[i][j] = 0;
			}
		}
		return false;
	}

	// Remove the K no. of digits to
	// complete game
	public void removeKDigits() {
		int count = missingDigits;
		while (count != 0) {
			int cellId = randomGenerator(size * size);

			// extract coordinates i  and j
			int i = (cellId / size);
			int j = cellId % BOARD_SIZE;
			if (j != 0)
				j = j - 1;

			if (i >= 9 || j >= 9) continue;

//            System.out.println(i+" "+j);
			if (generatedBoard[i][j] != 0) {
				count--;
				generatedBoard[i][j] = 0;
			}
		}
	}

	// Print sudoku
	public void printSudoku() {
//        for (int i = 0; i< size; i++)
//        {
//            for (int j = 0; j< size; j++)
//                System.out.print(solvedCopy[i][j] + " ");
//            System.out.println();
//        }
//        System.out.println();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				System.out.print(generatedBoard[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public int[][] getGeneratedBoard() {
		return generatedBoard;
	}


	// Driver code
	public static void main(String[] args) {
		SudokuGenerator sudoku = SudokuGenerator.getInstance();
//        sudoku.initWithMissingDigits(15);
		sudoku.init();
//        sudoku.fillValues();
		sudoku.printSudoku();
	}
}
