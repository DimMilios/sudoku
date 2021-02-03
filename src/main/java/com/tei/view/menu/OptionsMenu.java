package com.tei.view.menu;

import com.tei.model.SudokuConstants;
import com.tei.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsMenu extends JMenu {

	private final MainView mainView;

	private JMenu historySubMenu;
	private JMenuItem showHistory;
	private JMenuItem hideHistory;

	public OptionsMenu(String name, MainView mainView) {
		super(name);
		this.mainView = mainView;
		init();
	}

	private void init() {
		historySubMenu = new JMenu("History");
		showHistory = new JMenuItem("Show History");
		showHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.pushRoute(SudokuConstants.HISTORY_PANEL);
			}
		});
		hideHistory = new JMenuItem("Hide History");
		hideHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.pushRoute(SudokuConstants.GAME_PANEL);
			}
		});

		historySubMenu.add(showHistory);
		historySubMenu.add(hideHistory);
		this.add(historySubMenu);
	}
}
