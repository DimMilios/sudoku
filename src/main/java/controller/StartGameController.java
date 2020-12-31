package controller;

import dao.Dao;
import model.BoardModel;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameController implements ActionListener {

	private MainView mainView;

	public StartGameController(MainView mainView) {
		this.mainView = mainView;
		init();
	}

	private void init() {
		this.mainView.getUserPanel().getStartButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonGroup group = this.mainView.getUserPanel().getDifficultyGroup();

		String difficulty = group.getSelection().getActionCommand();

		// Add check for username
		this.mainView.getCardLayout().show(
				this.mainView.getCardsContainer(), MainView.GAME_PANEL);
	}


}
