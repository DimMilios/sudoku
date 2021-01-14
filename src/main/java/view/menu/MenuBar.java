package view.menu;

import view.MainView;

import javax.swing.*;

import static model.SudokuConstants.*;

public class MenuBar extends JMenuBar {

    private JMenu game;
    private JMenu options;
    private JMenu help;
    private MainView mainView;

    public MenuBar(MainView mainView) {
        this.mainView = mainView;
        initComponents();
    }

    private void initComponents() {
        game = MenuFactory.createMenu(GAME_MENU);
        options = MenuFactory.createMenu(OPTIONS_MENU);
        help = MenuFactory.createMenu(HELP_MENU);

        this.add(game);
        this.add(options);
        this.add(help);
    }
}
