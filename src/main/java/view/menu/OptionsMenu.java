package view.menu;

import javax.swing.*;

public class OptionsMenu extends JMenu {

    private JMenu historySubMenu;
    private JMenuItem showHistory;
    private JMenuItem hideHistory;

    public OptionsMenu(String name) {
        super(name);
        init();
    }

    private void init() {
        historySubMenu = new JMenu("History");
        showHistory = new JMenuItem("Show History");
        hideHistory = new JMenuItem("Hide History");

        historySubMenu.add(showHistory);
        historySubMenu.add(hideHistory);
        this.add(historySubMenu);
    }
}
