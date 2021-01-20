package controller;

import handler.FieldValueHandler;
import model.*;
import view.DefaultFieldState;
import view.IncorrectFieldState;
import view.MainView;
import view.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class BoardController {
	private final MainView mainView;
	private final SudokuGenerator sudokuGenerator = SudokuGenerator.getInstance();
	private final DifficultyFactory difficultyFactory;
	private final BoardModel boardModel;
	private KeyListener fieldValueHandler;

	public BoardController(MainView mainView,
						   DifficultyFactory difficultyFactory,
						   BoardModel boardModel) {
		this.mainView = mainView;
		this.difficultyFactory = difficultyFactory;
		this.boardModel = boardModel;
		this.fieldValueHandler = new FieldValueHandler(boardModel, mainView, this);
	}

	public void initializeBoard(String difficulty) {
		update(difficulty);
		addFieldListeners(fieldValueHandler);
		mainView.getCardLayout().show(mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);
	}

	public void addFieldListeners(KeyListener listener) {
		TextField[][] textFields = mainView.getGamePanel().getBoardPanel().getTextFields();
		for (TextField[] fields : textFields) {
			for (TextField field : fields) {
				field.addKeyListener(listener);
			}
		}
	}

	private int[][] generateBoard(String difficulty) {
		sudokuGenerator.initWithMissingDigits(
				difficultyFactory.getDifficultyStrategy(difficulty).getDifficulty());
		return sudokuGenerator.getGeneratedBoard();
	}

	public void update(String difficulty) {
//		System.out.println(boardModel.toString());
		boardModel.setDifficulty(difficulty);
		boardModel.setState(generateBoard(difficulty));
//		System.out.println(boardModel.toString());
	}

	public void updateField(int gridX, int gridY, int keyValue) {
//		System.out.println(boardModel.getState()[gridX][gridY]);
		boardModel.setField(gridX, gridY, keyValue);
//		System.out.println(boardModel.getState()[gridX][gridY]);
	}

	public void updateField(TextField textField, int keyValue) {
		textField.setText(String.valueOf(keyValue));

		int gridX = textField.getGridX();
		int gridY = textField.getGridY();

		if (!SudokuValidator.isSafe(boardModel.getState(), gridX, gridY, keyValue)) {
			IncorrectFieldState fieldState = new IncorrectFieldState(textField);
			textField.setFieldState(fieldState);
		}
		else {
			DefaultFieldState fieldState = new DefaultFieldState(textField);
			textField.setFieldState(fieldState);
			updateField(textField.getGridX(), textField.getGridY(), keyValue);
		}
	}
}
