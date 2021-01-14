package controller;

import model.*;
import view.GamePanel;
import view.MainView;
import view.TextField;
import view.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameController implements ActionListener {

	private final BoardModel boardModel;
	private final UserModel userModel;
	private final MainView mainView;
	private final TextFieldController textFieldController;
	private final SudokuGenerator sudokuGenerator;
	private final DifficultyFactory difficultyFactory;



	public StartGameController(BoardModel boardModel,
							   UserModel userModel, MainView mainView,
							   TextFieldController textFieldController,
							   SudokuGenerator sudokuGenerator,
							   DifficultyFactory difficultyFactory) {
		this.boardModel = boardModel;
		this.userModel = userModel;
		this.mainView = mainView;
		this.textFieldController = textFieldController;
		this.sudokuGenerator = sudokuGenerator;
		this.difficultyFactory = difficultyFactory;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserPanel userPanel = this.mainView.getUserPanel();
		GamePanel gamePanel = this.mainView.getGamePanel();

		ButtonGroup group = userPanel.getDifficultyGroup();
		String difficulty = group.getSelection().getActionCommand();

		String username = userPanel.getNameField().getText();
		username = validateUsername(username);

		boardModel.setState(generateBoard(difficulty));

		gamePanel.setup();

		for (view.TextField[] fields : mainView.getGamePanel().getTextFields()) {
			for (TextField field : fields) {
				field.addKeyListener(textFieldController);
			}
		}

		userModel.setUsername(username);
		gamePanel.getUsernameLabel().setText(userModel.getUsername());

		this.mainView.getCardLayout().show(
				this.mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);

	}

	private String validateUsername(String username) {
		while (username.length() < 3) {
			username = JOptionPane.showInputDialog(mainView,
												   "Username missing or too short",
												   "Error",
												   JOptionPane.ERROR_MESSAGE);
		}
		return username;
	}

	private int[][] generateBoard(String difficulty) {
		sudokuGenerator.initWithMissingDigits(
				difficultyFactory.getDifficultyStrategy(difficulty).getDifficulty());
		return sudokuGenerator.getGeneratedBoard();
	}


}
