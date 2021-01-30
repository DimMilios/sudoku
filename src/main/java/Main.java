import com.formdev.flatlaf.FlatLightLaf;
import controller.BoardController;
import controller.UserController;
import model.*;
import view.MainView;
import view.menu.MenuBar;

import javax.swing.*;
import java.awt.*;

public class Main {

	BoardController boardController;
	UserController userController;
	BoardModel boardModel;

	MainView mainView;

	DifficultyFactory difficultyFactory;

	public Main() {
		UIManager.put("FormattedTextField.inactiveForeground", new Color(0, 0, 0));
		difficultyFactory = new DifficultyFactory();

		boardModel = new BoardModel();
		mainView = new MainView(boardModel);

		userController = new UserController(mainView);
		boardController = new BoardController(mainView, difficultyFactory, boardModel);

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
