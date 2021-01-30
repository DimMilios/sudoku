package view.game;

import controller.BoardController;
import handler.TimeLabelHandler;
import model.BoardModel;
import model.BoardModel.BoardModelItem;
import model.SudokuValidator;
import observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;

import static model.SudokuConstants.*;

// A panel containing all the 3x3 subsquares
public class BoardPanel extends JPanel implements Observer {
	private final int rows;
	private final int cols;

	private BoardModel boardModel;
	private JPanel[][] panels = new JPanel[SQUARE_SIZE][SQUARE_SIZE];
	private TextField[][] textFields;
	private boolean gameOver;
	private BoardController boardController;
	private BoardModelItem currentModelItem;

	public BoardPanel(int rows,
					  int cols,
					  BoardModel boardModel) {
		this.rows = rows;
		this.cols = cols;
		this.boardModel = boardModel;
		this.boardModel.subscribe(this);
		setup(boardModel.first());
	}

	public void setup(BoardModelItem item) {
		this.textFields = new TextField[rows][cols];
		this.gameOver = false;
		this.setLayout(new GridLayout(SQUARE_SIZE, SQUARE_SIZE));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.currentModelItem = item;

		JLabel timeLabel = new JLabel();
		timeLabel.setFont(new Font("Arial", Font.BOLD, 26));
		timeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(timeLabel);

		LocalDateTime date = LocalDateTime.now();
		TimeLabelHandler timeLabelHandler = new TimeLabelHandler(date, timeLabel);
		Timer timer = new Timer(1000, timeLabelHandler);

		timer.start();

		addSquarePanels();
		addTextFields();
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

	public TextField[][] getTextFields() {
		return textFields;
	}

	public void setBoardController(BoardController boardController) {
		this.boardController = boardController;
	}

	public BoardModelItem getCurrentModelItem() {
		return currentModelItem;
	}

	public void setCurrentModelItem(BoardModelItem currentModelItem) {
		this.currentModelItem = currentModelItem;
	}

	@Override
	public void update(Object state) {
		currentModelItem = (BoardModelItem) state;
		System.out.println("Updated value=================\n" + currentModelItem);

		if (gameOver) return;

//		if (currentModelItem == null) {
//			currentModelItem = (BoardModelItem) state;
//		}

		if (SudokuValidator.isSolved(currentModelItem.getState())) {
			JOptionPane.showMessageDialog(this,
										  "Congratulations! You have completed the board.",
										  "Winner",
										  JOptionPane.INFORMATION_MESSAGE);
//			boardController.pushRoute(USER_PANEL);
			gameOver = true;
			return;
		}

	}

}
