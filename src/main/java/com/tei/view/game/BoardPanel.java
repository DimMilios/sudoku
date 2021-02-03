package com.tei.view.game;

import com.tei.controller.BoardController;
import com.tei.controller.FieldController;
import com.tei.handler.FieldValueHandler;
import com.tei.model.BoardModel;
import com.tei.model.BoardModel.BoardModelItem;
import com.tei.model.SudokuValidator;
import com.tei.observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;

import static com.tei.model.SudokuConstants.*;

// A panel containing all the 3x3 subsquares
public class BoardPanel extends JPanel implements Observer {
	private final int rows;
	private final int cols;

	private final BoardModel boardModel;
	private final JPanel[][] panels = new JPanel[SQUARE_SIZE][SQUARE_SIZE];
	private TextField[][] textFields;
	private boolean gameOver;
	private BoardController boardController;
	private FieldController fieldController;
	private FieldValueHandler fieldHandler;
	private BoardModelItem currentModelItem;

	public BoardPanel(int rows,
					  int cols,
					  BoardModel boardModel) {
		this.rows = rows;
		this.cols = cols;
		this.boardModel = boardModel;
		this.boardModel.subscribe(this);
	}

	public void setup(BoardModelItem item) {
		this.textFields = new TextField[rows][cols];
		this.gameOver = false;
		this.setLayout(new GridLayout(SQUARE_SIZE, SQUARE_SIZE));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.currentModelItem = item;
		addSquarePanels();
		addTextFields();
		this.fieldHandler = new FieldValueHandler(boardModel, this, fieldController);
		addFieldListeners(fieldHandler);

	}

	private void addSquarePanels() {
		for (int i = 0; i < SQUARE_SIZE; i++) {
			addSquarePanel(i);
		}
	}

	private void addSquarePanel(int i) {
		for (int j = 0; j < SQUARE_SIZE; j++) {
			panels[i][j] = new SquarePanel();
			this.add(panels[i][j]);
		}
	}

	private void addTextFields() {
		for (int i = 0; i < rows; i++) {
			addTextField(i);
		}
	}

	private void addTextField(int i) {
		for (int j = 0; j < cols; j++) {
			TextField textField = new TextField(i, j, currentModelItem.getState()[i][j]);
			textFields[i][j] = textField;
			panels[i / SQUARE_SIZE][j / SQUARE_SIZE].add(textField);
		}
	}

	public void addFieldListeners(KeyListener listener) {
		for (TextField[] fields : textFields) {
			for (TextField field : fields) {
				field.addKeyListener(listener);
			}
		}
	}

	public TextField[][] getTextFields() {
		return textFields;
	}

	public void setBoardController(BoardController boardController) {
		this.boardController = boardController;
	}

	public void setFieldController(FieldController fieldController) {
		this.fieldController = fieldController;
	}

	public BoardModelItem getCurrentModelItem() {
		return currentModelItem;
	}

	@Override
	public void update(Object state) {
		currentModelItem = (BoardModelItem) state;
//		System.out.println("Updated value=================\n" + currentModelItem);

		if (gameOver) return;

		if (SudokuValidator.isSolved(currentModelItem.getState())) {
			stopTimer();
			gameOver = true;
			int option = getOption();
			performOptionAction(option);
		}

	}

	private void performOptionAction(int option) {
		switch (option) {
			case 0:
				boardController.startNewGame();
				break;
			case 1:
				boardController.restartBoard();
				break;
		}
	}

	private int getOption() {
		Object[] options = {"New Game", "Restart", "Cancel"};

		return JOptionPane.showOptionDialog(null,
											"Congratulations! You have completed the board.",
											"Winner",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.INFORMATION_MESSAGE,
											null,
											options,
											options[2]);
	}

	private void stopTimer() {
		Container innerGamePanel = SwingUtilities.getAncestorOfClass(InnerGamePanel.class, this);
		if (innerGamePanel instanceof InnerGamePanel) {
			InnerGamePanel panel = (InnerGamePanel) innerGamePanel;
			Timer timer = panel.getTimer();
			timer.stop();
			boardController.updateWithFinishTime(LocalDateTime.now());
		}
	}

}
