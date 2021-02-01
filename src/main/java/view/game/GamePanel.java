package view.game;

import model.BoardModel;
import model.UserModel;
import view.DifficultyLabel;
import view.UsernameLabel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

	private JLabel headerLabel;
	private UsernameLabel usernameLabel;
	private DifficultyLabel difficultyLabel;

	private InnerGamePanel innerGamePanel;

	private final BoardModel boardModel;
	private final UserModel userModel;

	public GamePanel(BoardModel boardModel, UserModel userModel) {
		this.boardModel = boardModel;
		this.userModel = userModel;
		this.setup();
	}

	public void setup() {
		this.setLayout(new BorderLayout());
		initHeader();
		this.usernameLabel = new UsernameLabel(userModel);
		this.difficultyLabel = new DifficultyLabel(boardModel);

		JPanel info = new JPanel(new BorderLayout());
		info.add(usernameLabel, BorderLayout.WEST);
		info.add(difficultyLabel, BorderLayout.EAST);
		this.add(info, BorderLayout.SOUTH);
	}

	public void setInnerGamePanel(InnerGamePanel innerGamePanel) {
		this.innerGamePanel = innerGamePanel;
		this.add(innerGamePanel, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	public InnerGamePanel getInnerGamePanel() {
		return innerGamePanel;
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
