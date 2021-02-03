package com.tei.model;

public class NormalDifficultyStrategy implements DifficultyStrategy {
	@Override
	public int getDifficulty() {
		return SudokuConstants.NORMAL_MISSING_DIGITS;
	}
}
