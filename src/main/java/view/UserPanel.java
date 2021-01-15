package view;

import controller.BoardController;
import controller.UserController;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.SudokuConstants.*;

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
        nameLabel = new JLabel("Enter your username:");
        nameField = new JTextField();

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
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String difficulty = difficultyGroup.getSelection().getActionCommand();
                String username = nameField.getText();
                userController.update(username);
                boardController.initializeBoard(difficulty);
            }
        });

        this.add(nameLabel);
        this.add(nameField);
        this.add(difficultyLabel);
        this.add(easy);
        this.add(normal);
        this.add(hard);
        this.add(startButton);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public ButtonGroup getDifficultyGroup() {
        return difficultyGroup;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
