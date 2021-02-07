package com.tei.handler;

import com.tei.controller.BoardController;
import com.tei.model.BoardModelItem;
import com.tei.view.UserPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonHandler implements ActionListener {

	private final UserPanel userPanel;

	public StartButtonHandler(UserPanel userPanel) {
		this.userPanel = userPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String difficulty = userPanel.getDifficultyGroup().getSelection().getActionCommand();
		String username = userPanel.getNameField().getText();
		userPanel.getUserController().updateUserModelWith(username);

		BoardController boardController = userPanel.getBoardController();
		BoardModelItem item = boardController.initializeBoard(difficulty);
		boardController.persist(item);
	}
}
