package view.menu;

import javax.swing.*;

public class GameMenu extends JMenu {

    private JMenuItem newGame;
    private JMenuItem restart;
    private JMenuItem exit;

    public GameMenu(String name) {
        super(name);
        init();
    }

    private void init() {
        newGame = new JMenuItem("New Game");
        restart = new JMenuItem("Restart");
        exit = new JMenuItem("Exit");


        this.add(newGame);
        this.add(restart);
        this.add(exit);
    }

}
