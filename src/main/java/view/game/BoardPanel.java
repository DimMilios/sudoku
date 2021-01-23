package view.game;

import controller.BoardController;
import model.BoardModel;
import model.BoardModel.BoardModelItem;
import model.SudokuValidator;
import observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static model.SudokuConstants.*;

// A panel containing all the 3x3 subsquares
public class BoardPanel extends JPanel implements Observer {
	private final int rows;
	private final int cols;

	private BoardModel boardModel;
	private JPanel[][] panels = new JPanel[SQUARE_SIZE][SQUARE_SIZE];
	private TextField[][] textFields;
	private boolean gameOver;
	private boolean isInitRound = true;
	private BoardController boardController;
	private BoardModelItem currentModelItem;

	public BoardPanel(int rows,
					  int cols,
					  BoardModel boardModel) {
		this.rows = rows;
		this.cols = cols;
		this.boardModel = boardModel;
		this.boardModel.subscribe(this);
//		setup();
	}

	public void setup(BoardModelItem item) {
		this.textFields = new TextField[rows][cols];
		this.gameOver = false;
//		this.isInitRound = true;
		this.setLayout(new GridLayout(SQUARE_SIZE, SQUARE_SIZE));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.currentModelItem = item;
		addSquarePanels();
		addTextFields(item);
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

	private void addTextFields(BoardModelItem item) {
		for (int i = 0; i < rows; i++) {
			addTextField(item, i);
		}
	}

	private void addTextField(BoardModelItem item, int i) {
		for (int j = 0; j < cols; j++) {
			TextField textField = new TextField(i, j, currentModelItem.getState()[i][j]);
			textFields[i][j] = textField;
			panels[i / SQUARE_SIZE][j / SQUARE_SIZE].add(textField);
		}
	}

	private void updateTextfields(int[][] state) {
//		for (JPanel[] panel : panels) {
//			for (JPanel panel1 : panel) {
//				panel1.removeAll();
//			}
//		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				TextField textField = new TextField(i, j, state[i][j]);
				textFields[i][j] = textField;
				panels[i / SQUARE_SIZE][j / SQUARE_SIZE].add(textField);
			}
		}
	}

	public TextField[][] getTextFields() {
		return textFields;
	}

	public void setBoardController(BoardController boardController) {
		this.boardController = boardController;
	}

	@Override
	public void update(Object state) {
//		System.out.println(boardModel);
//		java.util.List<BoardModelItem> item = (java.util.List<BoardModelItem>) state;
//		BoardModelItem item = (BoardModelItem) state;

		if (gameOver) return;

		if (currentModelItem == null) {
			currentModelItem = (BoardModelItem) state;
		}

		if (SudokuValidator.isSolved(currentModelItem.getState())) {
			JOptionPane.showMessageDialog(this,
										  "Congratulations! You have completed the board.",
										  "Winner",
										  JOptionPane.INFORMATION_MESSAGE);
//			boardController.pushRoute(USER_PANEL);
			gameOver = true;
			return;
		}
		else {
			boardController.restartBoard();
////			boardController.restartBoard(item.get(item.size() - 1));
		}
//		if (isInitRound) {
//			boardController.restartBoard();
//			isInitRound = false;
//////			addTextFields();
//		}
//		else {
//			updateTextfields(arr);
//		}
	}

}
