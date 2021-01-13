package model;

import java.util.HashMap;
import java.util.Map;

import static model.SudokuConstants.*;

public class DifficultyFactory {
	private final static Map<String, DifficultyStrategy> strategies = new HashMap<>();

	static {
		strategies.put(EASY, new EasyDifficultyStrategy());
		strategies.put(NORMAL, new NormalDifficultyStrategy());
		strategies.put(HARD, new HardDifficultyStrategy());
	}

	public DifficultyStrategy getDifficultyStrategy(String difficulty) {
		if (!strategies.containsKey(difficulty)) {
			return new EasyDifficultyStrategy();
		}
		return strategies.get(difficulty);
	}
}
