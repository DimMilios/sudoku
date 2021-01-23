package view.menu;

import controller.BoardController;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.SudokuConstants.*;

public class GameMenu extends JMenu {

    private JMenuItem newGame;
    private JMenuItem restart;
    private JMenuItem exit;
    private BoardController boardController;

    public GameMenu(String name, BoardController boardController) {
        super(name);
        this.boardController = boardController;
        init();
    }

    private void init() {
        newGame = new JMenuItem(NEW_GAME);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardController.startNewGame();
            }
        });

        restart = new JMenuItem(RESTART);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardController.restartBoard();
            }
        });

        exit = new JMenuItem(EXIT);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(newGame);
        this.add(restart);
        this.add(exit);
    }

}
