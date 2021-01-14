package view.menu;

import javax.swing.*;

import static model.SudokuConstants.*;

public class GameMenu extends JMenu {

    private JMenuItem newGame;
    private JMenuItem restart;
    private JMenuItem exit;

    public GameMenu(String name) {
        super(name);
        init();
    }

    private void init() {
        newGame = new JMenuItem(NEW_GAME);
        restart = new JMenuItem(RESTART);
        exit = new JMenuItem(EXIT);

        this.add(newGame);
        this.add(restart);
        this.add(exit);
    }

}
