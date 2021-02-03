package com.tei.view.menu;

import com.tei.view.MainView;
import com.tei.controller.BoardController;

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
//
//    public static JMenuItem createMenuItem(String name) {
//        switch (name) {
//            case NEW_GAME:
//                return new MenuItem(name);
//            case RESTART:
//            case EXIT:
//            case SHOW_HISTORY:
//            case HIDE_HISTORY:
//            case ABOUT:
//        }
//    }
}
