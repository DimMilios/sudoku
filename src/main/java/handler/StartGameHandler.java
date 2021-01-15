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

	private final MainView mainView;

	private final UserController userController;
	private final BoardController boardController;


	public StartGameHandler(MainView mainView,
							UserController userController,
							BoardController boardController) {
		this.mainView = mainView;
		this.userController = userController;
		this.boardController = boardController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserPanel userPanel = this.mainView.getUserPanel();

		ButtonGroup group = userPanel.getDifficultyGroup();
		String difficulty = group.getSelection().getActionCommand();

		String username = userPanel.getNameField().getText();
		userController.update(username);


		boardController.update(difficulty);

//		for (TextField[] fields : gamePanel.getTextFields()) {
//			for (TextField field : fields) {
//				field.addKeyListener(fieldValueHandler);
//			}
//		}

		this.mainView.getCardLayout().show(
				this.mainView.getCardsContainer(), SudokuConstants.GAME_PANEL);

	}

}
