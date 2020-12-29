package view.menu;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private JMenu game;
    private JMenu options;
    private JMenu help;

    public MenuBar() {
        initComponents();
    }

    private void initComponents() {
        game = MenuFactory.createMenuItem("Game");
        options = MenuFactory.createMenuItem("Options");
        help = MenuFactory.createMenuItem("Help");

        this.add(game);
        this.add(options);
        this.add(help);
    }
}
