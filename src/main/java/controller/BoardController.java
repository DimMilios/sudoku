package controller;

import model.*;
import model.BoardModel.BoardModelItem;
import view.game.*;
import view.MainView;

import java.util.Arrays;

import static model.SudokuConstants.*;

public class BoardController {
	private final MainView mainView;
	private final SudokuGenerator sudokuGenerator = SudokuGenerator.getInstance();
	private final DifficultyFactory difficultyFactory;
	private final BoardModel boardModel;
	private String boardDifficulty;

	public BoardController(MainView mainView,
						   DifficultyFactory difficultyFactory,
						   BoardModel boardModel) {
		this.mainView = mainView;
		this.difficultyFactory = difficultyFactory;
		this.boardModel = boardModel;
	}

	public void initializeBoard(String difficulty) {
		update(difficulty);
		BoardPanel panel = new BoardPanel(BOARD_SIZE, BOARD_SIZE, boardModel);
		panel.setBoardController(this);
		InnerGamePanel innerGamePanel = new InnerGamePanel(panel);
		innerGamePanel.setup(boardModel.first());
		this.mainView.getGamePanel().setInnerGamePanel(innerGamePanel);

		this.mainView.getCardLayout().show(mainView.getCardsContainer(), GAME_PANEL);
		this.boardDifficulty = difficulty;
	}

	private void update(String difficulty) {
		BoardModelItem item = new BoardModelItem(difficulty, generateBoard(difficulty));
		boardModel.add(item);
	}

	private int[][] generateBoard(String difficulty) {
		sudokuGenerator.initWithMissingDigits(
				difficultyFactory.getDifficultyStrategy(difficulty).getDifficulty());
		return sudokuGenerator.getGeneratedBoard();
	}

	public void startNewGame() {
		GamePanel gamePanel = mainView.getGamePanel();
		gamePanel.remove(gamePanel.getInnerGamePanel());
		boardModel.clear();
		boardModel.unsubscribe(gamePanel.getInnerGamePanel().getBoardPanel());
		initializeBoard(boardDifficulty);
	}

	public void restartBoard() {
		GamePanel gamePanel = mainView.getGamePanel();
		gamePanel.remove(gamePanel.getInnerGamePanel());
		boardModel.unsubscribe(gamePanel.getInnerGamePanel().getBoardPanel());
		BoardPanel panel = new BoardPanel(BOARD_SIZE, BOARD_SIZE, boardModel);
		panel.setBoardController(this);
		InnerGamePanel innerGamePanel = new InnerGamePanel(panel);
		gamePanel.setInnerGamePanel(innerGamePanel);

		innerGamePanel.setup(boardModel.first());
		mainView.getCardLayout().show(mainView.getCardsContainer(), GAME_PANEL);
		innerGamePanel.revalidate();
		innerGamePanel.repaint();
	}

	public void updateField(TextField textField, int keyValue, BoardModelItem currentItem) {
		textField.setText(String.valueOf(keyValue));

		int gridX = textField.getGridX();
		int gridY = textField.getGridY();

		int[][] currentItemState = currentItem.getState();

		if (!SudokuValidator.isSafe(currentItemState, gridX, gridY, keyValue)) {
			textField.setFieldState(FieldStateFactory.create(INCORRECT_STATE, textField));
		}
		else {
			textField.setFieldState(FieldStateFactory.create(DEFAULT_STATE, textField));
			int[][] newState = new int[9][9];
			for (int i = 0; i < currentItemState.length; i++) {
				newState[i] = Arrays.copyOf(currentItemState[i], currentItemState[i].length);
			}
			newState[gridX][gridY] = keyValue;
			BoardModelItem item = new BoardModelItem(currentItem.getDifficulty(), newState);
			boardModel.add(item);
		}
	}

	public void pushRoute(String route) {
		mainView.getCardLayout()
				.show(mainView.getCardsContainer(), route);
	}
}
