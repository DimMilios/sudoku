package com.tei.handler;

import com.tei.controller.FieldController;
import com.tei.model.BoardModel;
import com.tei.model.BoardModelItem;
import com.tei.view.game.*;
import com.tei.view.game.TextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FieldValueHandler implements KeyListener {

	private final BoardModel boardModel;
	private final BoardPanel boardPanel;
	private final FieldController fieldController;

	private TextField textField;

	public FieldValueHandler(BoardModel boardModel,
							 BoardPanel boardPanel,
							 FieldController fieldController) {
		this.boardModel = boardModel;
		this.boardPanel = boardPanel;
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
		if (keyPressIsNumeric(keyCode)) {
			writeNumberValue(e);
		} else if (keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_DELETE) {
			resetField();
		} else {
			moveWithArrowKeys(keyCode);
		}
	}

	private boolean keyPressIsNumeric(int keyCode) {
		return (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_9)
				|| (keyCode >= KeyEvent.VK_NUMPAD1 && keyCode <= KeyEvent.VK_NUMPAD9);
	}

	private void writeNumberValue(KeyEvent e) {
		if (!textField.isEditable()) return;

		int keyValue = Character.getNumericValue(e.getKeyChar());
		BoardModelItem currentModelItem = boardPanel.getCurrentModelItem();
		fieldController.updateField(textField, keyValue, currentModelItem);
	}

	private void resetField() {
		if (!textField.isEditable()
				|| textField.getValue() == null) return;
		fieldController.resetToDefault(textField);
	}

	private void moveWithArrowKeys(int keyCode) {
		int oldX = textField.getPosition().getX();
		int oldY = textField.getPosition().getY();

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
			default:
				return;
		}

		focusNextTextField(nextX, nextY);
	}

	private void focusNextTextField(int nextX, int nextY) {
		TextField nextField = boardPanel.getTextFields()[nextX][nextY];
		nextField.requestFocusInWindow();
	}

}
