package controller;

import dao.BoardDAO;
import dao.GameDAO;
import model.*;
import model.BoardModel.BoardModelItem;
import view.game.*;
import view.MainView;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static model.SudokuConstants.*;

public class BoardController {
	private final MainView mainView;
	private final SudokuGenerator sudokuGenerator = SudokuGenerator.getInstance();
	private final DifficultyFactory difficultyFactory;
	private final BoardDAO boardDao;
	private final BoardModel boardModel;
	private String boardDifficulty;

	private final GameDAO gameDAO;

	public BoardController(MainView mainView,
						   DifficultyFactory difficultyFactory,
						   BoardDAO boardDAO, BoardModel boardModel, GameDAO gameDAO) {
		this.mainView = mainView;
		this.difficultyFactory = difficultyFactory;
		this.boardDao = boardDAO;
		this.boardModel = boardModel;
		this.gameDAO = gameDAO;
	}

	public BoardModelItem initializeBoard(String difficulty) {
		BoardModelItem item = boardItemOf(difficulty);
		BoardPanel panel = new BoardPanel(BOARD_SIZE, BOARD_SIZE, boardModel);
		panel.setBoardController(this);
		InnerGamePanel innerGamePanel = new InnerGamePanel(panel);
		innerGamePanel.setup(item);
		this.mainView.getGamePanel().setInnerGamePanel(innerGamePanel);

		this.mainView.getCardLayout().show(mainView.getCardsContainer(), GAME_PANEL);
		this.boardDifficulty = difficulty;
		return item;
	}

	private BoardModelItem boardItemOf(String difficulty) {
		BoardModelItem item = new BoardModelItem(difficulty, generateBoard(difficulty));
		boardModel.add(item);
		return item;
	}

	public void persist(BoardModelItem item) {
		boardDao.save(item);
		try {
			UserModel user = gameDAO.findCurrentUser();
			GamePojo toAdd = new GamePojo(user.getId(),
										  boardModel.getId(),
										  StartDateTimeSingleton.getInstance().getStartTime());
			gameDAO.save(toAdd);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
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
		BoardModelItem item = initializeBoard(boardDifficulty);
		persist(item);
	}

	public void restartBoard() {
		GamePanel gamePanel = mainView.getGamePanel();
		gamePanel.remove(gamePanel.getInnerGamePanel());
		boardModel.unsubscribe(gamePanel.getInnerGamePanel().getBoardPanel());
		BoardPanel panel = new BoardPanel(BOARD_SIZE, BOARD_SIZE, boardModel);
		panel.setBoardController(this);
		InnerGamePanel innerGamePanel = new InnerGamePanel(panel);
		gamePanel.setInnerGamePanel(innerGamePanel);

		BoardModelItem item = boardModel.first();
		boardModel.setId(boardModel.getId() + 1);
		StartDateTimeSingleton.getInstance().setStartTime(LocalDateTime.now());
		persist(item);

		innerGamePanel.setup(item);
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
		} else {
			textField.setFieldState(FieldStateFactory.create(DEFAULT_STATE, textField));
			int[][] newState = new int[currentItemState.length][currentItemState[0].length];
			for (int i = 0; i < currentItemState.length; i++) {
				newState[i] = Arrays.copyOf(currentItemState[i], currentItemState[i].length);
			}
			newState[gridX][gridY] = keyValue;
			BoardModelItem item = new BoardModelItem(currentItem.getDifficulty(), newState);
			boardModel.add(item);
		}
	}

	public void updateWithFinishTime(LocalDateTime finishTime) {
		try {
			UserModel currentUser = gameDAO.findCurrentUser();
			GamePojo game = new GamePojo(currentUser.getId(), boardModel.getId());
			game.setFinishTime(finishTime);
			gameDAO.update(game);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
