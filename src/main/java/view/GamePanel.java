package view;

import handler.FieldValueHandler;
import handler.LabelResizeHandler;
import model.BoardModel;
import model.UserModel;
import observer.EventType;
import observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static model.SudokuConstants.*;

public class GamePanel extends JPanel implements Observer {

	private JLabel headerLabel;
	private JLabel usernameLabel;
	private JPanel textFieldPanel;
	private TextField[][] textFields = new TextField[BOARD_SIZE][BOARD_SIZE];
	private JPanel[][] panels = new JPanel[SQUARE_SIZE][SQUARE_SIZE];
	private FieldValueHandler fieldValueHandler;

	private BoardModel boardModel;

	public GamePanel(FieldValueHandler fieldValueHandler, BoardModel boardModel) {
		this.fieldValueHandler = fieldValueHandler;
		this.boardModel = boardModel;
		this.boardModel.subscribe(EventType.BOARD_UPDATE, this);
		this.init();
	}

	private void init() {
		this.setLayout(new BorderLayout());
		initHeader();
		this.usernameLabel = new JLabel(UserModel.getInstance().getUsername());
		this.usernameLabel.setFont(new Font("Arial", Font.BOLD, 26));

		this.textFieldPanel = new JPanel(new GridLayout(SQUARE_SIZE, SQUARE_SIZE));
		this.textFieldPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		createSquarePanels();
		this.add(textFieldPanel, BorderLayout.CENTER);


		this.addComponentListener(new LabelResizeHandler(usernameLabel));

		this.add(usernameLabel, BorderLayout.SOUTH);
	}

	public void setup() {
		initBoard();
	}

	private void initHeader() {
		JPanel headerPanel = new JPanel();
		this.headerLabel = new JLabel("Sudoku Game");
		this.headerLabel.setFont(new Font("Arial", Font.BOLD, 42));
		this.headerLabel.setForeground(new Color(240, 240, 240));
		headerPanel.setBackground(new Color(15, 15, 15));
		this.headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		headerPanel.setBounds(0, 0, this.getWidth(), (int) (this.getHeight() * 0.3));
		headerPanel.add(headerLabel);
		this.add(headerPanel, BorderLayout.NORTH);
	}

	private void initBoard() {

		addTextFieldsToSquarePanels();

//		for (int i = 0; i < BOARD_SIZE; i++) {
//			for (int j = 0; j < BOARD_SIZE; j++) {
//				textFields[i][j].addKeyListener(fieldValueHandler);
//			}
//		}
//		this.add(textFieldPanel, BorderLayout.CENTER);
	}

	private void createSquarePanels() {
		for (int i = 0; i < SQUARE_SIZE; i++) {
			for (int j = 0; j < SQUARE_SIZE; j++) {
				panels[i][j] = createSquarePanel();
				textFieldPanel.add(panels[i][j]);
			}
		}
	}

	private void addTextFieldsToSquarePanels() {
		int[][] arr = boardModel.getState();
		int cnt = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				int value = arr[i][j];

				TextField textField;
				if (value > 0) {
					textField = createTextField(i, j, String.valueOf(value));
					textFields[i][j] = textField;
//					textField.setEnabled(false);
					textField.setEditable(false);
					textField.setOpaque(true);
					textField.setBackground(new Color(255, 255, 255));
					textField.setFont(new Font("Arial", Font.BOLD, 24));
				} else {
					textField = createTextField(i, j, "");

					textFields[i][j] = textField;
				}
				panels[i / SQUARE_SIZE][j / SQUARE_SIZE].add(textField);


				if (arr[i][j] > 0) cnt++;
			}
		}

		System.out.println("Length: " + cnt);
	}

	private JPanel createSquarePanel() {
		JPanel panel = new JPanel(new GridLayout(SQUARE_SIZE, SQUARE_SIZE));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		return panel;
	}

	private TextField createTextField(int x, int y, String text)  {
		TextField textField = TextField.createTextField(x, y);
		textField.setText(text);
		return textField;
	}

	public TextField[][] getTextFields() {
		return textFields;
	}

	public JLabel getUsernameLabel() {
		return usernameLabel;
	}

	@Override
	public void update(EventType eventType) {
		switch (eventType) {
			case USERNAME_INSERT:
				this.usernameLabel.setText(UserModel.getInstance().getUsername());
				break;
			case BOARD_UPDATE:
				this.setup();
				break;
			default:
		}
	}
}
