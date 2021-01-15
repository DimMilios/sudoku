package view;

import handler.FieldValueHandler;
import model.BoardModel;
import view.menu.MenuBar;

import javax.swing.*;
import java.awt.*;

import static model.SudokuConstants.*;

public class MainView extends JFrame {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    private BoardModel boardModel;
    private CardLayout cardLayout;
    private JPanel cardsContainer;

    private UserPanel userPanel;
    private GamePanel gamePanel;
    private HistoryPanel historyPanel;
    private FieldValueHandler fieldValueHandler;

    public MainView(BoardModel boardModel, FieldValueHandler fieldValueHandler) {
        super("Sudoku");
        this.boardModel = boardModel;
        this.fieldValueHandler = fieldValueHandler;
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        cardsContainer = new JPanel(cardLayout);

        this.setJMenuBar(new MenuBar(this));

        this.userPanel = new UserPanel();
        this.gamePanel = new GamePanel(fieldValueHandler, boardModel);
        this.historyPanel = new HistoryPanel();

        cardsContainer.add(userPanel, USER_PANEL);
        cardsContainer.add(gamePanel, GAME_PANEL);
        cardsContainer.add(historyPanel, HISTORY_PANEL);

        cardLayout.show(cardsContainer, USER_PANEL);

        this.getContentPane().add(cardsContainer);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JPanel getCardsContainer() {
        return cardsContainer;
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
