package handler;

import view.UserPanel;

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
		userPanel.getUserController().update(username);
		userPanel.getBoardController().initializeBoard(difficulty);
	}
}
