package controller;

import handler.FieldValueHandler;
import model.*;
import view.game.*;
import view.MainView;

import java.awt.event.KeyListener;

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

	public void initializeBoard() {
		GamePanel gamePanel = mainView.getGamePanel();
		addFieldListeners(fieldValueHandler);
		mainView.getCardLayout().show(mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);
		gamePanel.revalidate();
		gamePanel.repaint();
	}

	public void initializeBoard(String difficulty) {
		update(difficulty);
		this.boardDifficulty = difficulty;
		addFieldListeners(fieldValueHandler);
		mainView.getCardLayout().show(mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);
	}

	public void update(String difficulty) {
		boardModel.setDifficulty(difficulty);
		boardModel.setState(generateBoard(difficulty));
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
		initializeBoard(boardDifficulty);
		boardPanel.setup();
		addFieldListeners(fieldValueHandler);
		mainView.getCardLayout().show(mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);
		boardPanel.revalidate();
		boardPanel.repaint();
	}

	public void restartBoard() {
		BoardPanel boardPanel = mainView.getGamePanel().getBoardPanel();
		boardPanel.removeAll();
		boardPanel.setup();
		addFieldListeners(fieldValueHandler);
		mainView.getCardLayout().show(mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);
		boardPanel.revalidate();
		boardPanel.repaint();
	}


	public void updateField(int gridX, int gridY, int keyValue) {
//		System.out.println(boardModel.getState()[gridX][gridY]);
		System.out.println(boardModel.printState(boardModel.getState()));
		boardModel.setField(gridX, gridY, keyValue);
		System.out.println(boardModel.printState(boardModel.getState()));
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

	public void pushRoute(String route) {
		mainView.getCardLayout()
				.show(mainView.getCardsContainer(), route);
	}
}
