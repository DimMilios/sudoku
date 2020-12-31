package model;

public class SudokuValidator {

	private static SudokuValidator instance = null;

	private SudokuValidator() {
	}

	public static SudokuValidator getInstance() {
		if (instance == null) {
			instance = new SudokuValidator();
		}
		return instance;
	}

	public static boolean isSafe(int[][] grid, int row, int col, int num) {
		return (!usedIncol(grid, col, num)
				&& !usedInRow(grid, row, num)
				&& !usedInBox(grid, row - row % 3, col - col % 3, num));

	}

	private static boolean usedInRow(int[][] board, int row, int num) {
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == num) {
				System.out.println("Violation in row check\nRow number: " + row + " Col number: " + i);
				return true;
			}
		}
		return false;
	}

	private static boolean usedIncol(int[][] board, int col, int num) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == num) {
				System.out.println("Violation in column check\nRow number: " + i + " Col number: " + col);
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
					System.out.println(
							"Violation in square check\nRow number: " + (row + row1Start) + " Col number: " + (col + col1Start));
					return true;
				}
		return false;

	}
}