package view;

import model.BoardModel;
import model.SudokuValidator;
import observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static model.SudokuConstants.BOARD_SIZE;
import static model.SudokuConstants.SQUARE_SIZE;

// A panel containing all the 3x3 subsquares
public class BoardPanel extends JPanel implements Observer {
	private int rows;
	private int cols;

	private BoardModel boardModel;
	private JPanel[][] panels = new JPanel[SQUARE_SIZE][SQUARE_SIZE];
	private TextField[][] textFields;
	private boolean initRound = true;

	public BoardPanel(int rows, int cols, BoardModel boardModel) {
		this.rows = rows;
		this.cols = cols;
		this.boardModel = boardModel;
		this.boardModel.subscribe(this);
		init();
	}

	private void init() {
		this.textFields = new TextField[rows][cols];
		this.setLayout(new GridLayout(SQUARE_SIZE, SQUARE_SIZE));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		addSquarePanels();
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

	private void addTextFields(int[][] state) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			addTextField(state, i);
		}
	}

	private void addTextField(int[][] state, int i) {
		for (int j = 0; j < BOARD_SIZE; j++) {
			TextField textField = new TextField(i, j, state[i][j]);
			textFields[i][j] = textField;
			panels[i / SQUARE_SIZE][j / SQUARE_SIZE].add(textField);
		}
	}

	private void updateTextfields(int[][] state) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				textFields[i][j].setText(String.valueOf(state[i][j]));
			}
		}
	}

	public TextField[][] getTextFields() {
		return textFields;
	}

	@Override
	public void update(Object state) {
//		System.out.println(boardModel);
		int[][] arr = (int[][]) state;

		if (SudokuValidator.isSolved(arr)) {
			JOptionPane.showMessageDialog(this,
										  "Congratulations! You have completed the board.",
										  "Winner",
										   JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (initRound) {
			addTextFields(arr);
		} else {
			updateTextfields(arr);
		}
	}

}
