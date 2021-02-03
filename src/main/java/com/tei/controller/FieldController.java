package com.tei.controller;

import com.tei.dao.FieldDAO;
import com.tei.log.StdOutHandler;
import com.tei.model.BoardModel;
import com.tei.model.FieldModel;
import com.tei.model.SudokuValidator;
import com.tei.view.MainView;
import com.tei.view.game.FieldStateFactory;
import com.tei.view.game.TextField;

import java.util.Arrays;
import java.util.logging.Logger;

import static com.tei.model.BoardModel.*;
import static com.tei.model.SudokuConstants.DEFAULT_STATE;
import static com.tei.model.SudokuConstants.INCORRECT_STATE;

public class FieldController {
	private final BoardModel boardModel;
	private final FieldDAO fieldDAO;
	private final MainView mainView;

	private static Logger LOGGER = Logger.getLogger(FieldController.class.getName());

	static {
		LOGGER.setUseParentHandlers(false);
		StdOutHandler sh = new StdOutHandler();
		LOGGER.addHandler(sh);
	}

	public FieldController(BoardModel boardModel,
						   MainView mainView,
						   FieldDAO fieldDAO) {
		this.boardModel = boardModel;
		this.mainView = mainView;
		this.fieldDAO = fieldDAO;
	}

	public void updateField(TextField textField, int keyValue, BoardModelItem currentItem) {
		textField.setText(String.valueOf(keyValue));

		int gridX = textField.getGridX();
		int gridY = textField.getGridY();

		int[][] currentItemState = currentItem.getState();

		if (!SudokuValidator.isSafe(currentItemState, gridX, gridY, keyValue)) {
			textField.setFieldState(FieldStateFactory.create(INCORRECT_STATE, textField));
		} else {
			textField.setFieldState(FieldStateFactory.create(DEFAULT_STATE, textField));
			int[][] newState = copyState(currentItemState);
			newState[gridX][gridY] = keyValue;
			FieldModel fieldModel = new FieldModel(gridX, gridY, keyValue);
			fieldDAO.save(fieldModel);
			LOGGER.info("New field move: " + fieldModel.toString());
			BoardModelItem item = new BoardModelItem(currentItem.getDifficulty(), newState);
			boardModel.add(item);
		}
	}

	private int[][] copyState(int[][] currentItemState) {
		int[][] newState = new int[currentItemState.length][currentItemState[0].length];
		for (int i = 0; i < currentItemState.length; i++) {
			newState[i] = Arrays.copyOf(currentItemState[i], currentItemState[i].length);
		}
		return newState;
	}

}
