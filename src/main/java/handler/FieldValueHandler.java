package handler;

import controller.BoardController;
import model.BoardModel;
import view.DisabledFieldState;
import view.MainView;
import view.TextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FieldValueHandler implements KeyListener {

	private BoardModel boardModel;
	private MainView mainView;
	private BoardController boardController;

	private TextField textField;

	public FieldValueHandler(BoardModel boardModel,
							 MainView mainView,
							 BoardController boardController) {
		this.boardModel = boardModel;
		this.mainView = mainView;
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
			writeNumberValue(e, keyCode);
		}
		else {
			moveWithArrowKeys(keyCode);
		}
	}

	private void writeNumberValue(KeyEvent e, int keyCode) {
//		if (!textField.isEditable()) return;
//		System.out.println(textField.getFieldState().getClass());
		if (!(textField.getFieldState() instanceof DisabledFieldState)) {
			// Replace textField text when pressing keys 1-9 or numpad 1-9
			int keyValue = Character.getNumericValue(e.getKeyChar());
			boardController.updateField(textField, keyValue);
		}


//		textField.setText(String.valueOf(keyValue));
//
//		int gridX = textField.getGridX();
//		int gridY = textField.getGridY();
//
//		if (!SudokuValidator.isSafe(boardModel.getState(), gridX, gridY, keyValue)) {
//			textField.setBorder(
//					BorderFactory.createLineBorder(new Color(200, 20, 20), 3));
//		} else {
//			textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder(
//					"TextField.border"));
//
////			boardController.updateField(gridX, gridY, keyValue);
//		}
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
		TextField nextField = mainView.getGamePanel().getBoardPanel().getTextFields()[nextX][nextY];
		nextField.requestFocusInWindow();
	}

	private boolean keyIsNumeric(int keyCode) {
		return (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_9)
				|| (keyCode >= KeyEvent.VK_NUMPAD1 && keyCode <= KeyEvent.VK_NUMPAD9);
	}
}
