import com.formdev.flatlaf.FlatLightLaf;
import controller.BoardController;
import controller.FieldController;
import controller.UserController;
import handler.FieldValueHandler;
import handler.StartGameHandler;
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
	FieldValueHandler fieldValueHandler;
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

		mainView = new MainView(boardModel, fieldValueHandler);
		UserController userController = new UserController(mainView);
		BoardController boardController = new BoardController(mainView,
															  difficultyFactory,
															  boardModel);
		FieldController fieldController = new FieldController(boardModel);

		fieldValueHandler = new FieldValueHandler(boardModel, mainView, fieldController);
		startGameHandler = new StartGameHandler(userModel,
												mainView, fieldValueHandler,
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
