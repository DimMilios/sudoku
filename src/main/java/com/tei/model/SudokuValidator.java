package com.tei.model;

import static com.tei.model.SudokuConstants.*;

public class SudokuValidator {

	public static boolean isSolved(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] < 1 || board[i][j] > 9)
					return false;
			}
		}
		return true;
	}

	public static boolean isSafe(int[][] board, int row, int col, int num) {
		return (!usedInCol(board, col, num)
				&& !usedInRow(board, row, num)
				&& !usedInBox(board, row - row % SQUARE_SIZE, col - col % SQUARE_SIZE, num));

	}

	private static boolean usedInRow(int[][] board, int row, int num) {
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == num) {
//				System.out.println("Number: " + num + " found in row: " + row);
				return true;
			}
		}
		return false;
	}

	private static boolean usedInCol(int[][] board, int col, int num) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == num) {
//				System.out.println("Number: " + num + " found in col: " + col);
				return true;
			}
		}
		return false;
	}

	public static boolean usedInBox(int[][] board,
									 int row1Start,
									 int col1Start,
									 int num) {
		for (int row = 0; row < SQUARE_SIZE; row++)
			for (int col = 0; col < SQUARE_SIZE; col++)
				if (board[row + row1Start][col + col1Start] == num) {
//					System.out.println("Number: " + num + " found in row: " + (row + row1Start)
//											   + " and col: "  + (col + col1Start));
					return true;
				}
		return false;

	}
}