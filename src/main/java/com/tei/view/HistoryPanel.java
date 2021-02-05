package com.tei.view;

import com.tei.log.MessageConsole;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {

	private JTextArea historyTextArea;
	private JScrollPane scrollPane;
	private JLabel headerLabel;

	public HistoryPanel() {
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());
		initHeader();
		historyTextArea = new JTextArea();
		historyTextArea.setFont(new Font("Consolas", Font.PLAIN, 16));
		scrollPane = new JScrollPane(historyTextArea);

		MessageConsole messageConsole = new MessageConsole(historyTextArea);
		messageConsole.redirectOut(null, System.out);
//		messageConsole.redirectErr(null, System.err);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		historyTextArea.setEditable(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		this.add(scrollPane, BorderLayout.CENTER);
	}

	private void initHeader() {
		JPanel headerPanel = new JPanel();
		this.headerLabel = new JLabel("Game History");
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
