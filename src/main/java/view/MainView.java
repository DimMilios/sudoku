package view;

import controller.TextFieldController;
import model.SudokuGenerator;
import view.menu.MenuBar;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    private SudokuGenerator generator;

    private CardLayout cardLayout;
    private JPanel cardsContainer;

    public final static String USER_PANEL = "User panel";
    public final static String GAME_PANEL = "Game panel";
    public final static String HISTORY_PANEL = "History panel";

    private UserPanel userPanel;
    private GamePanel gamePanel;
    private HistoryPanel historyPanel;
    private TextFieldController fieldController;

    public MainView(SudokuGenerator generator) {
        super("Sudoku");
        this.generator = generator;
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        cardsContainer = new JPanel(cardLayout);

        this.setJMenuBar(new MenuBar());

        this.userPanel = new UserPanel();
        this.gamePanel = new GamePanel(fieldController, generator);
        this.historyPanel = new HistoryPanel();

        cardsContainer.add(userPanel, USER_PANEL);
        cardsContainer.add(gamePanel, GAME_PANEL);
        cardsContainer.add(historyPanel, HISTORY_PANEL);

        cardLayout.show(cardsContainer, GAME_PANEL);

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

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public UserPanel getUserPanel() {
        return userPanel;
    }

    public void setUserPanel(UserPanel userPanel) {
        this.userPanel = userPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public HistoryPanel getHistoryPanel() {
        return historyPanel;
    }

    public void setHistoryPanel(HistoryPanel historyPanel) {
        this.historyPanel = historyPanel;
    }

    public void setFieldController(TextFieldController fieldController) {
        this.fieldController = fieldController;
        initComponents();
    }
}
