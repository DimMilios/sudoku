package view.menu;

import controller.BoardController;
import view.MainView;

import javax.swing.*;

import static model.SudokuConstants.*;

public class MenuBar extends JMenuBar {

    private JMenu game;
    private JMenu options;
    private JMenu help;
    private final MainView mainView;
    private final BoardController boardController;

    public MenuBar(MainView mainView, BoardController boardController) {
        this.mainView = mainView;
        this.boardController = boardController;
        initComponents();
    }

    private void initComponents() {
        game = MenuFactory.createMenu(GAME_MENU, boardController);
        options = MenuFactory.createMenu(OPTIONS_MENU, boardController);
        help = MenuFactory.createMenu(HELP_MENU, boardController);

        this.add(game);
        this.add(options);
        this.add(help);
    }
}
