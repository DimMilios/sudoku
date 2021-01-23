package view.game;

import model.BoardModel;
import view.DifficultyLabel;
import view.UsernameLabel;

import javax.swing.*;
import java.awt.*;

import static model.SudokuConstants.*;

public class GamePanel extends JPanel {

	private JLabel headerLabel;
	private UsernameLabel usernameLabel;
	private DifficultyLabel difficultyLabel;
	private BoardPanel boardPanel;

	private BoardModel boardModel;

	public GamePanel(BoardModel boardModel) {
		this.boardModel = boardModel;
		this.setup();
	}

	public void setup() {
		this.setLayout(new BorderLayout());
		initHeader();
		this.usernameLabel = new UsernameLabel();
		this.difficultyLabel = new DifficultyLabel(boardModel);

		this.boardPanel = new BoardPanel(BOARD_SIZE, BOARD_SIZE, boardModel);
		this.add(boardPanel, BorderLayout.CENTER);

		JPanel info = new JPanel(new BorderLayout());
		info.add(usernameLabel, BorderLayout.WEST);
		info.add(difficultyLabel, BorderLayout.EAST);
		this.add(info, BorderLayout.SOUTH);
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
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
}
