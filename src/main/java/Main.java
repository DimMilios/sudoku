import com.formdev.flatlaf.FlatLightLaf;
import controller.StartGameController;
import controller.TextFieldController;
import dao.Dao;
import dao.InMemoryBoardDaoImpl;
import model.BoardModel;
import model.SudokuGenerator;
import model.SudokuValidator;
import view.MainView;

import javax.swing.*;

public class Main {

    BoardModel boardModel;

    MainView mainView;

    StartGameController startGameController;
    TextFieldController textFieldController;
    SudokuGenerator generator;

    public Main() {
        boardModel = new BoardModel();
        generator = SudokuGenerator.getInstance();
        generator.init();
        boardModel.setState(generator.getMat());

        mainView = new MainView(generator);

        textFieldController = new TextFieldController(boardModel, mainView);

        mainView.setFieldController(textFieldController);
        startGameController = new StartGameController(mainView);

//        mainView.setVisible(true);
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
