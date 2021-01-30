package handler;

import controller.BoardController;
import model.BoardModel;
import view.game.BoardPanel;
import view.MainView;
import view.game.TextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FieldValueHandler implements KeyListener {

	private BoardModel boardModel;
	private BoardPanel boardPanel;
	private BoardController boardController;

	private TextField textField;

	public FieldValueHandler(BoardModel boardModel,
							 BoardPanel boardPanel,
							 BoardController boardController) {
		this.boardModel = boardModel;
		this.boardPanel = boardPanel;
		this.boardController = boardController;
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


		if (keyIsNumeric(keyCode)) {
			writeNumberValue(e);
		}
		else {
			moveWithArrowKeys(keyCode);
		}
	}

	private void writeNumberValue(KeyEvent e) {
		if (!textField.isEditable()) return;

		int keyValue = Character.getNumericValue(e.getKeyChar());
		BoardModel.BoardModelItem currentModelItem = boardPanel.getCurrentModelItem();
		boardController.updateField(textField, keyValue, currentModelItem);
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
			default:
				return;
		}

		// Focus next field on arrow key move
		TextField nextField = boardPanel.getTextFields()[nextX][nextY];
		nextField.requestFocusInWindow();
	}

	private boolean keyIsNumeric(int keyCode) {
		return (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_9)
				|| (keyCode >= KeyEvent.VK_NUMPAD1 && keyCode <= KeyEvent.VK_NUMPAD9);
	}
}
