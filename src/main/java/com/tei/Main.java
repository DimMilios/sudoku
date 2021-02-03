package com.tei;

import com.formdev.flatlaf.FlatLightLaf;
import com.tei.controller.BoardController;
import com.tei.controller.FieldController;
import com.tei.controller.UserController;
import com.tei.dao.*;
import com.tei.database.MySQLConnection;
import com.tei.model.BoardModel;
import com.tei.model.DifficultyFactory;
import com.tei.model.UserModel;
import com.tei.view.MainView;
import com.tei.view.menu.MenuBar;

import javax.swing.*;
import java.awt.*;

public class Main {

	BoardController boardController;
	UserController userController;
	FieldController fieldController;
	BoardModel boardModel;
	UserModel userModel;

	MainView mainView;

	DifficultyFactory difficultyFactory;

	public Main() {
		UIManager.put("FormattedTextField.inactiveForeground", new Color(0, 0, 0));
		System.setProperty("java.util.logging.SimpleFormatter.format",
						   "[%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS] %4$s %5$s%6$s%n");
		difficultyFactory = new DifficultyFactory();

		boardModel = new BoardModel();
		userModel = new UserModel();
		mainView = new MainView(boardModel, userModel);

		MySQLConnection mySQLConnection = MySQLConnection.getInstance();
		UserDAO userDao = new UserDAOImpl(mySQLConnection);
		BoardDAO boardDao = new BoardDAOImpl(mySQLConnection, boardModel);
		GameDAO gameDao = new GameDAOImpl(mySQLConnection, boardModel);
		FieldDAO fieldDAO = new FieldDAOImpl(mySQLConnection);

		userController = new UserController(mainView, userDao, userModel);
		fieldController = new FieldController(boardModel, mainView, fieldDAO);
		boardController = new BoardController(mainView, difficultyFactory, boardDao, boardModel,
											  gameDao, fieldController);

		mainView.setJMenuBar(new MenuBar(mainView, boardController));

		mainView.getUserPanel().setUserController(userController);
		mainView.getUserPanel().setBoardController(boardController);
	}

	public static void main(String[] args) {
		FlatLightLaf.install();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}
}
