import com.formdev.flatlaf.FlatLightLaf;
import controller.BoardController;
import controller.FieldController;
import controller.UserController;
import handler.StartGameHandler;
import handler.FieldKeyHandler;
import model.*;
import view.MainView;

import javax.swing.*;
import java.awt.*;

import static model.SudokuConstants.*;

public class Main {

	BoardModel boardModel;
	UserModel userModel;

	MainView mainView;

	StartGameHandler startGameHandler;
	FieldKeyHandler fieldKeyHandler;
	SudokuGenerator generator;
	DifficultyFactory difficultyFactory;

	public Main() {
		UIManager.put("FormattedTextField.inactiveForeground", new Color(0, 0, 0));
		difficultyFactory = new DifficultyFactory();
		generator = SudokuGenerator.getInstance();

		generator.initWithMissingDigits(
				difficultyFactory.getDifficultyStrategy(EASY).getDifficulty());

//		boardModel = new BoardModel(EASY, generator.getGeneratedBoard());
		boardModel = new BoardModel();

		userModel = UserModel.getInstance();

		mainView = new MainView(boardModel, fieldKeyHandler);
		UserController userController = new UserController(mainView);
		BoardController boardController = new BoardController(mainView,
															  difficultyFactory,
															  boardModel);
		FieldController fieldController = new FieldController(boardModel);

		fieldKeyHandler = new FieldKeyHandler(boardModel, mainView, fieldController);
		startGameHandler = new StartGameHandler(userModel,
												mainView, fieldKeyHandler,
												userController,
												boardController);

		mainView.getUserPanel()
				.getStartButton().addActionListener(startGameHandler);

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
