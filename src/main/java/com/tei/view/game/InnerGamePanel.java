package com.tei.view.game;

import com.tei.handler.TimeLabelHandler;
import com.tei.model.StartDateTimeSingleton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import static com.tei.model.BoardModel.*;

public class InnerGamePanel extends JPanel {

	private final BoardPanel boardPanel;
	private JLabel timeLabel;
	private Timer timer;
	private JPanel utilPanel;
	private JButton undoButton;

	public InnerGamePanel(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
//		this.add(boardPanel);
	}

	public void setup(BoardModelItem item) {
		this.setLayout(new BorderLayout());
		timeLabel = new JLabel();
		timeLabel.setFont(new Font("Arial", Font.BOLD, 26));
		timeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
//		this.add(timeLabel, BorderLayout.SOUTH);

		boardPanel.setup(item);
		this.add(boardPanel, BorderLayout.CENTER);

		LocalDateTime date = LocalDateTime.now();
		StartDateTimeSingleton.getInstance().setStartTime(date);
		TimeLabelHandler timeLabelHandler = new TimeLabelHandler(date, timeLabel);
		timer = new Timer(0, timeLabelHandler);

		timer.start();

		initUtilPanel();
	}

	private void initUtilPanel() {
		utilPanel = new JPanel();
		utilPanel.add(timeLabel, BorderLayout.LINE_START);

		undoButton = new JButton("Undo");
		undoButton.setFont(new Font("Arial", Font.BOLD, 26));
//		undoButton.setPreferredSize(new Dimension(100, 60));
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardPanel.undo();
			}
		});
		utilPanel.add(undoButton, BorderLayout.LINE_END);
		this.add(utilPanel, BorderLayout.NORTH);
	}

	public Timer getTimer() {
		return timer;
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
}
