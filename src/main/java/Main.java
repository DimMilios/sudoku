import com.formdev.flatlaf.FlatLightLaf;
import controller.BoardController;
import controller.UserController;
import dao.*;
import database.MySQLConnection;
import model.*;
import view.MainView;
import view.menu.MenuBar;

import javax.swing.*;
import java.awt.*;

public class Main {

	BoardController boardController;
	UserController userController;
	BoardModel boardModel;
	UserModel userModel;

	MainView mainView;

	DifficultyFactory difficultyFactory;

	public Main() {
		UIManager.put("FormattedTextField.inactiveForeground", new Color(0, 0, 0));
		difficultyFactory = new DifficultyFactory();

		boardModel = new BoardModel();
		userModel = new UserModel();
		mainView = new MainView(boardModel, userModel);

		MySQLConnection mySQLConnection = MySQLConnection.getInstance();
		UserDAO userDao = new UserDAOImpl(mySQLConnection);
		BoardDAO boardDao = new BoardDAOImpl(mySQLConnection, boardModel);
		GameDAO gameDao = new GameDAOImpl(mySQLConnection, boardModel);

		userController = new UserController(mainView, userDao, userModel);
		boardController = new BoardController(mainView, difficultyFactory, boardDao, boardModel,
											  gameDao);

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
