package com.tei.view.menu;

import com.tei.controller.BoardController;
import com.tei.view.MainView;

import javax.swing.*;

import static com.tei.model.SudokuConstants.*;

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
        game = MenuFactory.createMenu(GAME_MENU, boardController, mainView);
        options = MenuFactory.createMenu(OPTIONS_MENU, boardController, mainView);
        help = MenuFactory.createMenu(HELP_MENU, boardController, mainView);

        this.add(game);
        this.add(options);
        this.add(help);
    }
}
