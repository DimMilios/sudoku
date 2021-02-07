package com.tei.model;

public final class BoardModelItem {
	private final String difficulty;
	private final int[][] state;

	public BoardModelItem(String difficulty, int[][] state) {
		this.difficulty = difficulty;
		this.state = state;
	}

	@Override
	public String toString() {
		return "BoardModel{" +
				"difficulty='" + difficulty + '\'' +
				", \nstate=\n" + printState(state);
	}

	public String printState(int[][] state) {
		String result = "";
		for (int i = 0; i < state.length; i++) {
			result += "[";
			for (int j = 0; j < state[i].length; j++) {
				result = result.concat(state[i][j] + " ");
			}
			result += "]\n";
		}
		return result;
	}

	public int getValueAt(int i, int j) {
		try {
			return this.state[i][j];
		} catch (Exception e) {
			return 0;
		}
	}

	public int[][] getState() {
		return state;
	}

	public String getDifficulty() {
		return difficulty;
	}
}
