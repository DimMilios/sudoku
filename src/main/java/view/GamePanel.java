package view;

import handler.LabelResizeHandler;
import handler.TextFieldKeyHandler;
import test.SudokuGenerator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

	private final static int gridSize = 9;
	private JLabel headerLabel;
	private JLabel usernameLabel;
	private JPanel textFieldPanel;
	private List<JFormattedTextField> textFields = new ArrayList<>();
	private JPanel[][] panels = new JPanel[3][3];

	private SudokuGenerator generator;

	public GamePanel() {
		generator = new SudokuGenerator(gridSize, 40);
		generator.fillValues();

		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());

		initHeader();

		try {
			initBoard();
		} catch (ParseException e) {
			e.printStackTrace();
		}

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

	private void initBoard() throws ParseException {
		this.textFieldPanel = new JPanel(new GridLayout(3, 3));

		this.textFieldPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JPanel squarePanel = createSquarePanel();
				panels[i][j] = squarePanel;
				textFieldPanel.add(panels[i][j]);
			}
		}

		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("#");
			mask.setInvalidCharacters("0");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int[][] arr = generator.getMat();
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				JFormattedTextField textField = createTextField(mask, String.valueOf(arr[i][j]));
				textFields.add(textField);
				panels[i / 3][j / 3].add(textField);
			}
		}

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				System.out.print(textFields.get(j + i * gridSize).getText() + " ");
			}
			System.out.println();
		}

		this.add(textFieldPanel, BorderLayout.CENTER);
	}

	private JPanel createSquarePanel() {
		JPanel panel = new JPanel(new GridLayout(3, 3));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		return panel;
	}

	private JFormattedTextField createTextField(MaskFormatter mask, String text)  {
		JFormattedTextField textField = new JFormattedTextField(mask);
		textField.setText(text);
		textField.setFont(new Font("Arial", Font.BOLD, 26));
		textField.setCaretColor(Color.WHITE);
		textField.addKeyListener(new TextFieldKeyHandler(textField));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		return textField;
	}


}
