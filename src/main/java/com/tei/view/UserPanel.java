package com.tei.view;

import com.tei.controller.BoardController;
import com.tei.controller.UserController;
import com.tei.handler.StartButtonHandler;

import javax.swing.*;

import static com.tei.model.SudokuConstants.*;

public class UserPanel extends JPanel {

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel difficultyLabel;
    private ButtonGroup difficultyGroup;
    private JRadioButton easy;
    private JRadioButton normal;
    private JRadioButton hard;
    private JButton startButton;

    private UserController userController;
    private BoardController boardController;

    public UserPanel() {
        initComponents();
    }

    private void initComponents() {

        JPanel namePanel = new JPanel();
        nameLabel = new JLabel("Enter your username:");
        nameField = new JTextField();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        difficultyLabel = new JLabel("Choose a difficulty:");
        easy = new JRadioButton(EASY, true);
        easy.setActionCommand(EASY);
        normal = new JRadioButton(NORMAL, false);
        normal.setActionCommand(NORMAL);
        hard = new JRadioButton(HARD, false);
        hard.setActionCommand(HARD);

        difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easy);
        difficultyGroup.add(normal);
        difficultyGroup.add(hard);

        startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonHandler(this));

//        this.add(nameLabel);
//        this.add(nameField);
        this.add(namePanel);
        this.add(difficultyLabel);
        this.add(easy);
        this.add(normal);
        this.add(hard);
        this.add(startButton);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public ButtonGroup getDifficultyGroup() {
        return difficultyGroup;
    }

    public UserController getUserController() {
        return userController;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
