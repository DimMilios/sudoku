package controller;

import model.BoardModel;
import view.MainView;
import view.game.TextField;

import java.awt.event.KeyListener;
import java.util.EventListener;

public class FieldController {
	private final BoardModel boardModel;
	private final MainView mainView;
	private final KeyListener fieldValueHandler;

	public FieldController(BoardModel boardModel,
						   MainView mainView,
						   KeyListener fieldValueHandler) {
		this.boardModel = boardModel;
		this.mainView = mainView;
		this.fieldValueHandler = fieldValueHandler;
	}

	public void updateField(int gridX, int gridY, int keyValue) {
//		boardModel.setField(gridX, gridY, keyValue);
	}

}
