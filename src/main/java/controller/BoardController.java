package controller;

import handler.FieldValueHandler;
import model.*;
import model.BoardModel.BoardModelItem;
import view.game.*;
import view.MainView;

import java.awt.event.KeyListener;
import java.util.Arrays;

public class BoardController {
	private final MainView mainView;
	private final SudokuGenerator sudokuGenerator = SudokuGenerator.getInstance();
	private final DifficultyFactory difficultyFactory;
	private final BoardModel boardModel;
	private KeyListener fieldValueHandler;
	private String boardDifficulty;

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
		this.boardDifficulty = difficulty;
	}

	private void update(String difficulty) {
		BoardModelItem item = new BoardModelItem(difficulty, generateBoard(difficulty));
		boardModel.add(item);
//		boardModel.setDifficulty(difficulty);
//		boardModel.setState(generateBoard(difficulty));
	}

	private int[][] generateBoard(String difficulty) {
		sudokuGenerator.initWithMissingDigits(
				difficultyFactory.getDifficultyStrategy(difficulty).getDifficulty());
		return sudokuGenerator.getGeneratedBoard();
	}

	public void addFieldListeners(KeyListener listener) {
		TextField[][] textFields = mainView.getGamePanel().getBoardPanel().getTextFields();
		for (TextField[] fields : textFields) {
			for (TextField field : fields) {
				field.addKeyListener(listener);
			}
		}
	}

	public void startNewGame() {
		BoardPanel boardPanel = mainView.getGamePanel().getBoardPanel();
		boardPanel.removeAll();
		boardModel.clear();
		initializeBoard(boardDifficulty);
		boardPanel.setup(boardModel.last());
		addFieldListeners(fieldValueHandler);
		mainView.getCardLayout().show(mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);
		boardPanel.revalidate();
		boardPanel.repaint();
	}

	public void restartBoard() {
		BoardPanel boardPanel = mainView.getGamePanel().getBoardPanel();
		boardPanel.removeAll();
//		System.out.println("Snapshots:\n" + boardModel.getSnapshots());
		System.out.println("==========First:\n" + boardModel.first() + "\n============");
		System.out.println("==========Last:\n" + boardModel.last() + "\n============");
		boardPanel.setup(boardModel.first());
//		boardPanel.setup(sudokuGenerator.getGeneratedBoard());
		addFieldListeners(fieldValueHandler);
		mainView.getCardLayout().show(mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);
		boardPanel.revalidate();
		boardPanel.repaint();
	}

//	public void restartBoard(BoardModelItem item) {
//		BoardPanel boardPanel = mainView.getGamePanel().getBoardPanel();
//		boardPanel.removeAll();
//		System.out.println("=======================Snapshots:\n" + boardModel.getSnapshots()
//		+ "\n=============================");
//		boardPanel.setup(item);
//		addFieldListeners(fieldValueHandler);
//		mainView.getCardLayout().show(mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);
//		boardPanel.revalidate();
//		boardPanel.repaint();
//	}

	public void updateField(int gridX, int gridY, int keyValue) {
//		System.out.println(boardModel.printState(boardModel.getState()));
		BoardModelItem lastItem = boardModel.last();
//		boardModel.setField(gridX, gridY, keyValue);

//		System.out.println(boardModel.printState(boardModel.getState()));
	}

	public void updateField(TextField textField, int keyValue) {
		BoardModelItem lastItem = boardModel.last();
		textField.setText(String.valueOf(keyValue));

		int gridX = textField.getGridX();
		int gridY = textField.getGridY();

		int[][] lastItemState = lastItem.getState();

		if (!SudokuValidator.isSafe(lastItemState, gridX, gridY, keyValue)) {
			IncorrectFieldState fieldState = new IncorrectFieldState(textField);
			textField.setFieldState(fieldState);
		}
		else {
			DefaultFieldState fieldState = new DefaultFieldState(textField);
			textField.setFieldState(fieldState);
			int[][] newState = new int[9][9];
			for (int i = 0; i < lastItemState.length; i++) {
				newState[i] = Arrays.copyOf(lastItemState[i], lastItemState[i].length);
			}
			newState[gridX][gridY] = keyValue;
			boardModel.add(new BoardModelItem(lastItem.getDifficulty(), newState));
		}
	}

//	public void updateField(TextField textField, int keyValue) {
//		textField.setText(String.valueOf(keyValue));
//
//		int gridX = textField.getGridX();
//		int gridY = textField.getGridY();
//
//		if (!SudokuValidator.isSafe(boardModel.getState(), gridX, gridY, keyValue)) {
//			IncorrectFieldState fieldState = new IncorrectFieldState(textField);
//			textField.setFieldState(fieldState);
//		}
//		else {
//			DefaultFieldState fieldState = new DefaultFieldState(textField);
//			textField.setFieldState(fieldState);
//			updateField(textField.getGridX(), textField.getGridY(), keyValue);
//		}
//	}

	public void pushRoute(String route) {
		mainView.getCardLayout()
				.show(mainView.getCardsContainer(), route);
	}
}
