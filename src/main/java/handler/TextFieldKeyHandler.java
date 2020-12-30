package handler;

import view.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashSet;

public class TextFieldKeyHandler implements KeyListener {

    private TextField textField;
    private int[][] board;

    public TextFieldKeyHandler(TextField textField, int[][] board) {
        this.textField = textField;
        this.board = board;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Replace textField text when pressing keys 1-9 or numpad 1-9
        if ((e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_9)
                || (e.getKeyCode() >= KeyEvent.VK_NUMPAD1 && e.getKeyCode() <= KeyEvent.VK_NUMPAD9)) {
            String value = String.valueOf(e.getKeyChar());
            textField.setText(value);
            System.out.println("TextField {x, y}: " +
                    textField.getGridX() + ", " + textField.getGridY() +
                    " Value: " + textField.getText());

            checkBoardValidity(value);
        }
    }

    public void checkBoardValidity(String value) {
        // Approach: The problem can be solved by checking the following conditions:
        //
        // Check if each row of the board[][] array stores only unique values from the range [1, 9] or not.
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                System.out.println("[i,j]: " + "[" + i + "," + j + "]" + " Value: " + board[i][j]);
//            }
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (String.valueOf(board[i][j]).equals(value) && textField.getGridX() == i) {
                    System.out.println("Violation in row check\nRow number: " + i + " Col number: " + j);
                    return;
                }
            }
        }
        // Check if each column of the board[][] array stores unique values from the range [1, 9] or not.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (String.valueOf(board[i][j]).equals(value) && textField.getGridY() == j) {
                    System.out.println("Violation in column check\nRow number: " + i + " Col number: " + j);
                    return;
                }
            }
        }
        // Check if all possible 3 × 3 submatrices of the board[][] array stores unique values from the range [1, 9] or not.
        for (int k = 0; k < board.length; k += 3) {
            for (int l = 0; l < board.length; l += 3) {

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (String.valueOf(board[i][j]).equals(value)) {
                            System.out.println("Violation in square check\nRow number: " + i + " Col number: " + j);
                            textField.setBorder(BorderFactory.createLineBorder(new Color(200, 20, 20), 3));
                            return;
                        } else {
                            textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        }
                    }
                }

            }
        }
    }


}
