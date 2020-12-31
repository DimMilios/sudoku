package controller;

import model.BoardModel;
import model.Model;
import model.SudokuValidator;
import view.MainView;
import view.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextFieldController implements KeyListener {

	private BoardModel boardModel;
	private MainView mainView;
	private TextField textField;
	private int[][] board;

	public TextFieldController(BoardModel boardModel, MainView mainView) {
		this.boardModel = boardModel;
		this.mainView = mainView;
		this.board = boardModel.getState();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() instanceof TextField) {
			textField = (TextField) e.getSource();
		} else {
			return;
		}

		// Replace textField text when pressing keys 1-9 or numpad 1-9
		if (sourceIsNumber(e)) {
			textField.setText(String.valueOf(e.getKeyChar()));
			System.out.println("TextField {x, y}: " +
									   textField.getGridX() + ", " + textField.getGridY() +
									   " Value: " + textField.getText());

			if (!SudokuValidator.isSafe(boardModel.getState(),
										textField.getGridX(),
										textField.getGridY(),
										Character.getNumericValue(
												e.getKeyChar()))) {
				textField.setBorder(BorderFactory.createLineBorder(new Color(200,
																			 20,
																			 20),
																   3));
			} else {
				textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder(
						"TextField.border"));
				board[textField.getGridX()][textField.getGridY()] = Character.getNumericValue(e.getKeyChar());
				boardModel.setState(board);
			}
		}
	}

	private boolean sourceIsNumber(KeyEvent e) {
		return (e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_9)
				|| (e.getKeyCode() >= KeyEvent.VK_NUMPAD1 && e.getKeyCode() <= KeyEvent.VK_NUMPAD9);
	}
}
