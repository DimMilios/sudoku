package controller;

import handler.FieldValueHandler;
import model.*;
import model.BoardModel.BoardModelItem;
import view.game.*;
import view.MainView;

import java.awt.event.KeyListener;
import java.util.Arrays;

import static model.SudokuConstants.*;

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
		BoardPanel panel = new BoardPanel(BOARD_SIZE, BOARD_SIZE, boardModel);
		this.mainView.getGamePanel().setBoardPanel(panel);
		addFieldListeners(fieldValueHandler);
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

	public void addFieldListeners(KeyListener listener) {
		TextField[][] textFields = mainView.getGamePanel().getBoardPanel().getTextFields();
		for (TextField[] fields : textFields) {
			for (TextField field : fields) {
				field.addKeyListener(listener);
			}
		}
	}

	public void startNewGame() {
		GamePanel boardPanel = mainView.getGamePanel();
		boardPanel.remove(mainView.getGamePanel().getBoardPanel());
		boardModel.clear();
		initializeBoard(boardDifficulty);
	}

//	}
	public void restartBoard() {
		BoardPanel boardPanel = mainView.getGamePanel().getBoardPanel();
		boardPanel.removeAll();
//		System.out.println("Snapshots:\n" + boardModel.getSnapshots());
//		System.out.println("==========First:\n" + boardModel.first() + "\n============");
//		System.out.println("==========Last:\n" + boardModel.last() + "\n============");
		boardPanel.setup(boardModel.first());
		addFieldListeners(fieldValueHandler);
		mainView.getCardLayout().show(mainView.getCardsContainer(), GAME_PANEL);
		boardPanel.revalidate();
		boardPanel.repaint();
	}

	public void updateField(TextField textField, int keyValue) {
//		BoardModelItem lastItem = boardModel.last();
		BoardModelItem lastItem = mainView.getGamePanel().getBoardPanel().getCurrentModelItem();
//		System.out.println("Last Item========\n\n" + lastItem);
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
			BoardModelItem item = new BoardModelItem(lastItem.getDifficulty(), newState);
			boardModel.add(item);
			mainView.getGamePanel().getBoardPanel().setCurrentModelItem(item);
//			System.out.println("After Update========\n\n" + item);
		}
	}

	public void pushRoute(String route) {
		mainView.getCardLayout()
				.show(mainView.getCardsContainer(), route);
	}
}
