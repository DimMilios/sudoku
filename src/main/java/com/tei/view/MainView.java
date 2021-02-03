package com.tei.view;

import com.tei.model.BoardModel;
import com.tei.model.UserModel;
import com.tei.view.game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static com.tei.model.SudokuConstants.*;

public class MainView extends JFrame {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    private final BoardModel boardModel;
    private final UserModel userModel;
    private CardLayout cardLayout;
    private JPanel cardsContainer;
    private String[] routes;

    private UserPanel userPanel;
    private GamePanel gamePanel;
    private HistoryPanel historyPanel;
    private AboutPanel aboutPanel;

    public MainView(BoardModel boardModel, UserModel userModel) {
        super("Sudoku");
        this.boardModel = boardModel;
        this.userModel = userModel;
        this.routes = new String[]{ USER_PANEL, GAME_PANEL, HISTORY_PANEL, ABOUT_PANEL };
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        cardsContainer = new JPanel(cardLayout);

        this.userPanel = new UserPanel();
        this.historyPanel = new HistoryPanel();
        this.aboutPanel = new AboutPanel();

        this.gamePanel = new GamePanel(boardModel, userModel);
        cardsContainer.add(gamePanel, GAME_PANEL);

        cardsContainer.add(userPanel, USER_PANEL);
        cardsContainer.add(historyPanel, HISTORY_PANEL);
        cardsContainer.add(aboutPanel, ABOUT_PANEL);

        cardLayout.show(cardsContainer, USER_PANEL);
//        cardLayout.show(cardsContainer, ABOUT_PANEL);

        this.getContentPane().add(cardsContainer);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void pushRoute(String route) {
        if (Arrays.asList(routes).contains(route)) {
            this.getCardLayout().show(this.cardsContainer, route);
        }
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public UserPanel getUserPanel() {
        return userPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
