package view.menu;

import javax.swing.*;

import static model.SudokuConstants.*;

public class MenuFactory {

    public static JMenu createMenu(String name) {
        switch (name) {
            case GAME_MENU:
                return new GameMenu(name);
            case OPTIONS_MENU:
                return new OptionsMenu(name);
            case HELP_MENU:
                return new HelpMenu(name);
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
