package com.tei.model;

public class EasyDifficultyStrategy implements DifficultyStrategy {

	@Override
	public int getDifficulty() {
		return SudokuConstants.EASY_MISSING_DIGITS;
	}
}
