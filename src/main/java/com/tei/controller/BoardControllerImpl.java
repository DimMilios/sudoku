package com.tei.controller;

import com.tei.model.*;
import com.tei.view.game.BoardPanel;
import com.tei.view.game.GamePanel;
import com.tei.view.game.InnerGamePanel;
import com.tei.dao.BoardDAO;
import com.tei.dao.GameDAO;
import com.tei.log.StdOutHandler;
import com.tei.model.BoardModel.BoardModelItem;
import com.tei.view.MainView;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import static com.tei.model.SudokuConstants.*;

public class BoardControllerImpl implements BoardController {
	private final MainView mainView;
	private final SudokuGenerator sudokuGenerator = SudokuGenerator.getInstance();
	private final DifficultyFactory difficultyFactory;
	private final BoardDAO boardDao;
	private final BoardModel boardModel;
	private String boardDifficulty;

	private final GameDAO gameDAO;
	private final FieldController fieldController;

	private static Logger LOGGER = Logger.getLogger(BoardControllerImpl.class.getName());

	static {
		LOGGER.setUseParentHandlers(false);
		StdOutHandler sh = new StdOutHandler();
		LOGGER.addHandler(sh);
	}

	public BoardControllerImpl(MainView mainView,
							   DifficultyFactory difficultyFactory,
							   BoardDAO boardDAO,
							   BoardModel boardModel,
							   GameDAO gameDAO,
							   FieldController fieldController) {
		this.mainView = mainView;
		this.difficultyFactory = difficultyFactory;
		this.boardDao = boardDAO;
		this.boardModel = boardModel;
		this.gameDAO = gameDAO;
		this.fieldController = fieldController;
	}

	@Override
	public BoardModelItem initializeBoard(String difficulty) {
		BoardModelItem item = boardItemOf(difficulty);
		BoardPanel panel = new BoardPanel(BOARD_SIZE, BOARD_SIZE, boardModel);
		panel.setBoardController(this);
		panel.setFieldController(fieldController);
		InnerGamePanel innerGamePanel = new InnerGamePanel(panel);
		innerGamePanel.setup(item);
		this.mainView.getGamePanel().setInnerGamePanel(innerGamePanel);

		this.mainView.pushRoute(GAME_PANEL);
		this.boardDifficulty = difficulty;
		LOGGER.info("New board of " + difficulty + " difficulty has been initialized.");
		return item;
	}

	private BoardModelItem boardItemOf(String difficulty) {
		BoardModelItem item = new BoardModelItem(difficulty, generateBoard(difficulty));
		boardModel.push(item);
		return item;
	}

	@Override
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

	@Override
	public void startNewGame() {
		GamePanel gamePanel = mainView.getGamePanel();
		gamePanel.remove(gamePanel.getInnerGamePanel());
		boardModel.clear();
		boardModel.unsubscribe(gamePanel.getInnerGamePanel().getBoardPanel());
		BoardModelItem item = initializeBoard(boardDifficulty);
		persist(item);
		LOGGER.info("New game has started.");
	}

	@Override
	public void restartBoard() {
		GamePanel gamePanel = mainView.getGamePanel();
		gamePanel.remove(gamePanel.getInnerGamePanel());
		boardModel.unsubscribe(gamePanel.getInnerGamePanel().getBoardPanel());
		BoardPanel panel = new BoardPanel(BOARD_SIZE, BOARD_SIZE, boardModel);
		panel.setBoardController(this);
		panel.setFieldController(fieldController);
		InnerGamePanel innerGamePanel = new InnerGamePanel(panel);
		gamePanel.setInnerGamePanel(innerGamePanel);

		BoardModelItem item = boardModel.first();
		boardModel.setId(boardModel.getId() + 1);
		StartDateTimeSingleton.getInstance().setStartTime(LocalDateTime.now());
		persist(item);

		innerGamePanel.setup(item);
		mainView.pushRoute(GAME_PANEL);
		innerGamePanel.revalidate();
		innerGamePanel.repaint();
		LOGGER.info("Game has been restarted.");
	}

	@Override
	public void persistWithFinishTime(LocalDateTime finishTime) {
		try {
			UserModel currentUser = gameDAO.findCurrentUser();
			GamePojo game = new GamePojo(currentUser.getId(), boardModel.getId());
			game.setFinishTime(finishTime);
			gameDAO.update(game);
			LOGGER.info("Game was finished.");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
