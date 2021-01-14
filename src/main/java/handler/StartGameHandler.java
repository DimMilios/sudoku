package handler;

import controller.BoardController;
import controller.UserController;
import model.*;
import view.GamePanel;
import view.MainView;
import view.TextField;
import view.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameHandler implements ActionListener {

	private final UserModel userModel;
	private final MainView mainView;
	private final FieldKeyHandler fieldKeyHandler;

	private final UserController userController;
	private final BoardController boardController;


	public StartGameHandler(UserModel userModel,
							MainView mainView,
							FieldKeyHandler fieldKeyHandler,
							UserController userController,
							BoardController boardController) {
		this.userModel = userModel;
		this.mainView = mainView;
		this.fieldKeyHandler = fieldKeyHandler;
		this.userController = userController;
		this.boardController = boardController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserPanel userPanel = this.mainView.getUserPanel();
		GamePanel gamePanel = this.mainView.getGamePanel();


		ButtonGroup group = userPanel.getDifficultyGroup();
		String difficulty = group.getSelection().getActionCommand();

		String username = userPanel.getNameField().getText();
		userController.update(username);


		boardController.update(difficulty);


//		boardModel.setState(generateBoard(difficulty));

//		gamePanel.setup();
//		gamePanel.getUsernameLabel().setText(userModel.getUsername());


		for (TextField[] fields : gamePanel.getTextFields()) {
			for (TextField field : fields) {
				field.addKeyListener(fieldKeyHandler);
			}
		}

		this.mainView.getCardLayout().show(
				this.mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);

	}

//	private int[][] generateBoard(String difficulty) {
//		sudokuGenerator.initWithMissingDigits(
//				difficultyFactory.getDifficultyStrategy(difficulty).getDifficulty());
//		return sudokuGenerator.getGeneratedBoard();
//	}


}
