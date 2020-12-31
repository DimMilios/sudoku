package view;

import handler.LabelResizeHandler;
import controller.TextFieldController;
import model.SudokuGenerator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GamePanel extends JPanel {

	private final static int gridSize = 9;
	private JLabel headerLabel;
	private JLabel usernameLabel;
	private JPanel textFieldPanel;
	private TextField[][] textFields = new TextField[gridSize][gridSize];
	private JPanel[][] panels = new JPanel[3][3];

	private TextFieldController fieldController;
	private SudokuGenerator generator;

	public GamePanel(TextFieldController fieldController, SudokuGenerator generator) {
		this.fieldController = fieldController;
		this.generator = generator;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());

		initHeader();

		initBoard();

		this.usernameLabel = new JLabel("PLACEHOLDER");

		this.addComponentListener(new LabelResizeHandler(usernameLabel));

		this.add(usernameLabel, BorderLayout.SOUTH);
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
		this.textFieldPanel = new JPanel(new GridLayout(3, 3));

		this.textFieldPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		createSquarePanels();

		addTextFieldsToSquarePanels();

		this.add(textFieldPanel, BorderLayout.CENTER);
	}

	private void createSquarePanels() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				panels[i][j] = createSquarePanel();
				textFieldPanel.add(panels[i][j]);
			}
		}
	}

	private void addTextFieldsToSquarePanels() {
		int[][] arr = generator.getMat();
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				TextField textField = createTextField(i, j, String.valueOf(arr[i][j]));
				textFields[i][j] = textField;
				panels[i / 3][j / 3].add(textField);
			}
		}
	}

	private JPanel createSquarePanel() {
		JPanel panel = new JPanel(new GridLayout(3, 3));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		return panel;
	}

	private TextField createTextField(int x, int y, String text)  {
		TextField textField = TextField.createTextField(x, y);
		textField.setText(text);
		textField.addKeyListener(fieldController);
		return textField;
	}


}
