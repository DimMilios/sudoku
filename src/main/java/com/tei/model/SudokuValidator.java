package com.tei.model;

public class SudokuValidator {

	public static boolean isSolved(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] < 1 || grid[i][j] > 9)
					return false;
			}
		}
		return true;
	}

	public static boolean isSafe(int[][] grid, int row, int col, int num) {
		return (!usedInCol(grid, col, num)
				&& !usedInRow(grid, row, num)
				&& !usedInBox(grid, row - row % 3, col - col % 3, num));

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

	public static boolean usedInBox(int[][] grid,
									 int row1Start,
									 int col1Start,
									 int num) {
		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++)
				if (grid[row + row1Start][col + col1Start] == num) {
//					System.out.println("Number: " + num + " found in row: " + (row + row1Start)
//											   + " and col: "  + (col + col1Start));
					return true;
				}
		return false;

	}
}