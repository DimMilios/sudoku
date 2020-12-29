package view.menu;

import javax.swing.*;

public class HelpMenu extends JMenu {

    private JMenuItem about;

    public HelpMenu(String name) {
        super(name);
        init();
    }

    private void init() {
        about = new JMenuItem("About");
        this.add(about);
    }
}
