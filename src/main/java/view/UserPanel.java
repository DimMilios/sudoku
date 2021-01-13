package view;

import javax.swing.*;

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

        this.add(nameLabel);
        this.add(nameField);
        this.add(difficultyLabel);
        this.add(easy);
        this.add(normal);
        this.add(hard);
        this.add(startButton);
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JLabel getDifficultyLabel() {
        return difficultyLabel;
    }

    public ButtonGroup getDifficultyGroup() {
        return difficultyGroup;
    }

    public JRadioButton getEasy() {
        return easy;
    }

    public JRadioButton getNormal() {
        return normal;
    }

    public JRadioButton getHard() {
        return hard;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
