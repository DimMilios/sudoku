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
		}

		int keyCode = e.getKeyCode();

		moveWithArrowKeys(keyCode);

		// Replace textField text when pressing keys 1-9 or numpad 1-9
		if (sourceIsNumber(keyCode)) {
			int keyValue = Character.getNumericValue(e.getKeyChar());

			textField.setText(String.valueOf(e.getKeyChar()));

			if (!SudokuValidator.isSafe(boardModel.getState(),
										textField.getGridX(),
										textField.getGridY(),
										keyValue)) {
				textField.setBorder(
						BorderFactory.createLineBorder(new Color(200, 20, 20), 3));
			} else {
				textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder(
						"TextField.border"));

				boardModel.getState()[textField.getGridX()][textField.getGridY()] = keyValue;
			}
		}
	}

	private void moveWithArrowKeys(int keyCode) {
		int oldX = textField.getGridX();
		int oldY = textField.getGridY();

		int nextX = oldX;
		int nextY = oldY;
		// Check for arrow key events
		switch (keyCode) {
			case KeyEvent.VK_UP:
				if (oldX > 0) {
					nextX = oldX - 1;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (oldX + 1 < 9) {
					nextX = oldX + 1;
				}
				break;
			case KeyEvent.VK_LEFT:
				if (oldY > 0) {
					nextY = oldY - 1;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (oldY + 1 < 9) {
					nextY = oldY + 1;
				}
				break;
		}

		TextField nextField = mainView.getGamePanel().getTextFields()[nextX][nextY];
		nextField.requestFocusInWindow();
	}

	private boolean sourceIsNumber(int keyCode) {
		return (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_9)
				|| (keyCode >= KeyEvent.VK_NUMPAD1 && keyCode <= KeyEvent.VK_NUMPAD9);
	}
}
