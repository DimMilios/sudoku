package com.tei.controller;

import com.tei.dao.FieldDAO;
import com.tei.log.StdOutHandler;
import com.tei.model.BoardModel;
import com.tei.model.BoardModelItem;
import com.tei.model.FieldPojo;
import com.tei.model.SudokuValidator;
import com.tei.view.MainView;
import com.tei.view.game.FieldStateFactory;
import com.tei.view.game.TextField;

import java.util.Arrays;
import java.util.logging.Logger;

import static com.tei.model.SudokuConstants.*;

public class FieldControllerImpl implements FieldController {
	private final BoardModel boardModel;
	private final FieldDAO fieldDAO;
	private final MainView mainView;

	private static Logger LOGGER = Logger.getLogger(FieldControllerImpl.class.getName());

	static {
		LOGGER.setUseParentHandlers(false);
		StdOutHandler sh = new StdOutHandler();
		LOGGER.addHandler(sh);
	}

	public FieldControllerImpl(BoardModel boardModel,
							   MainView mainView,
							   FieldDAO fieldDAO) {
		this.boardModel = boardModel;
		this.mainView = mainView;
		this.fieldDAO = fieldDAO;
	}

	@Override
	public void updateField(TextField textField, int keyValue, BoardModelItem currentItem) {
		int gridX = textField.getPosition().getX();
		int gridY = textField.getPosition().getY();

		int[][] currentItemState = currentItem.getState();
		textField.setValue(keyValue);

		if (!SudokuValidator.isSafe(currentItemState, gridX, gridY, keyValue)) {
			textField.setFieldState(FieldStateFactory.create(INCORRECT_STATE, textField));
		} else {
			textField.setFieldState(FieldStateFactory.create(DEFAULT_STATE, textField));
			int[][] newState = copyState(currentItemState);
			newState[gridX][gridY] = keyValue;
			FieldPojo fieldPojo = new FieldPojo(gridX, gridY, keyValue);
			fieldDAO.save(fieldPojo);
			LOGGER.info("New field move: " + fieldPojo.toString());
			BoardModelItem item = new BoardModelItem(currentItem.getDifficulty(), newState);
			boardModel.push(item);
		}
	}

	private int[][] copyState(int[][] currentItemState) {
		int[][] newState = new int[currentItemState.length][currentItemState[0].length];
		for (int i = 0; i < currentItemState.length; i++) {
			newState[i] = Arrays.copyOf(currentItemState[i], currentItemState[i].length);
		}
		return newState;
	}

	@Override
	public void resetToDefault(TextField textField) {
		if (boardModel.pop()) {
			textField.setFieldState(FieldStateFactory.create(EMPTY_STATE, textField));
			LOGGER.info("Field at: " + textField.getPosition() + "deleted");
		}
	}
}
