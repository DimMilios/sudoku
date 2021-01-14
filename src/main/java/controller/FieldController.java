package controller;

import model.BoardModel;

public class FieldController {
	private BoardModel boardModel;

	public FieldController(BoardModel boardModel) {
		this.boardModel = boardModel;
	}

	public void updateField(int gridX, int gridY, int keyValue) {
		boardModel.setField(gridX, gridY, keyValue);
	}
}
