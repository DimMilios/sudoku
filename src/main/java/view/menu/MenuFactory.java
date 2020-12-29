package view.menu;

import javax.swing.*;

public class MenuFactory {

    public static JMenu createMenuItem(String name) {
        switch (name) {
            case "Game":
                return new GameMenu(name);
            case "Options":
                return new OptionsMenu(name);
            case "Help":
                return new HelpMenu(name);
            default:
                return null;
        }
    }
}
