import com.formdev.flatlaf.FlatLightLaf;
import controller.StartGameController;
import controller.TextFieldController;
import model.*;
import view.MainView;
import view.TextField;

import javax.swing.*;
import java.awt.*;

import static model.SudokuConstants.*;

public class Main {

    BoardModel boardModel;
    UserModel userModel;

    MainView mainView;

    StartGameController startGameController;
    TextFieldController textFieldController;
    SudokuGenerator generator;
    DifficultyFactory difficultyFactory;

    public Main() {
        UIManager.put("FormattedTextField.inactiveForeground", new Color(0, 0, 0));
        difficultyFactory = new DifficultyFactory();
        generator = SudokuGenerator.getInstance();

        generator.initWithMissingDigits(
                difficultyFactory.getDifficultyStrategy(EASY).getDifficulty());

        boardModel = new BoardModel(EASY, generator.getGeneratedBoard());

        userModel = UserModel.getInstance();

//        generator.init();
        mainView = new MainView(boardModel);

        textFieldController = new TextFieldController(boardModel, mainView);
        startGameController = new StartGameController(boardModel,
                                                      userModel,
                                                      mainView, textFieldController,
                                                      generator, difficultyFactory);

        mainView.getUserPanel()
                .getStartButton().addActionListener(startGameController);



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
