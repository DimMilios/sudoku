package view.game;

import handler.TimeLabelHandler;
import model.StartDateTimeSingleton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;

import static model.BoardModel.*;

public class InnerGamePanel extends JPanel {

	private final BoardPanel boardPanel;
	private JLabel timeLabel;
	private Timer timer;

	public InnerGamePanel(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
//		this.add(boardPanel);
	}

	public void setup(BoardModelItem item) {
		this.setLayout(new BorderLayout());
		timeLabel = new JLabel();
		timeLabel.setFont(new Font("Arial", Font.BOLD, 26));
		timeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(timeLabel, BorderLayout.SOUTH);

		boardPanel.setup(item);
		this.add(boardPanel, BorderLayout.NORTH);

		LocalDateTime date = LocalDateTime.now();
		StartDateTimeSingleton.getInstance().setStartTime(date);
		TimeLabelHandler timeLabelHandler = new TimeLabelHandler(date, timeLabel);
		timer = new Timer(0, timeLabelHandler);

		timer.start();
	}

	public Timer getTimer() {
		return timer;
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
}
