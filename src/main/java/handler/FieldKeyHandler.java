package handler;

import controller.FieldController;
import model.BoardModel;
import model.SudokuValidator;
import view.MainView;
import view.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FieldKeyHandler implements KeyListener {

	private BoardModel boardModel;
	private MainView mainView;
	private final FieldController fieldController;

	private TextField textField;

	public FieldKeyHandler(BoardModel boardModel,
						   MainView mainView,
						   FieldController fieldController) {
		this.boardModel = boardModel;
		this.mainView = mainView;
		this.fieldController = fieldController;
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
		System.out.println(keyCode);

		moveWithArrowKeys(keyCode);

		// Replace textField text when pressing keys 1-9 or numpad 1-9
		if (sourceIsNumber(keyCode)) {
			int keyValue = Character.getNumericValue(e.getKeyChar());

			textField.setText(String.valueOf(e.getKeyChar()));

			int gridX = textField.getGridX();
			int gridY = textField.getGridY();

			if (!SudokuValidator.isSafe(boardModel.getState(), gridX, gridY, keyValue)) {
				textField.setBorder(
						BorderFactory.createLineBorder(new Color(200, 20, 20), 3));
			} else {
				textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder(
						"TextField.border"));

				fieldController.updateField(gridX, gridY, keyValue);
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

		// Focus next field on arrow key move
		TextField nextField = mainView.getGamePanel().getTextFields()[nextX][nextY];
		nextField.requestFocusInWindow();
	}

	private boolean sourceIsNumber(int keyCode) {
		return (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_9)
				|| (keyCode >= KeyEvent.VK_NUMPAD1 && keyCode <= KeyEvent.VK_NUMPAD9);
	}
}
