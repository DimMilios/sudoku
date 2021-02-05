package com.tei.view.menu;

import com.tei.controller.BoardController;
import com.tei.view.MainView;

import javax.swing.*;

import static com.tei.model.SudokuConstants.*;

public class MenuFactory {

    public static JMenu createMenu(String name, BoardController boardController, MainView mainView) {
        switch (name) {
            case GAME_MENU:
                return new GameMenu(name, boardController);
            case OPTIONS_MENU:
                return new OptionsMenu(name, mainView);
            case HELP_MENU:
                return new HelpMenu(name, mainView);
            default:
                return null;
        }
    }

}
