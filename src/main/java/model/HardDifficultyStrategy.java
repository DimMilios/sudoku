package model;

public class HardDifficultyStrategy implements DifficultyStrategy {
	@Override
	public int getDifficulty() {
		return SudokuConstants.HARD_MISSING_DIGITS;
	}
}
