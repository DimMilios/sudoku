package com.tei.view;

import com.tei.log.MessageConsole;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {

	private JTextArea historyTextArea;
	private JScrollPane scrollPane;

	public HistoryPanel() {
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());
		this.add(new JLabel("History"), BorderLayout.NORTH);
		historyTextArea = new JTextArea();
		historyTextArea.setFont(new Font("Monospace", Font.PLAIN, 14));
		scrollPane = new JScrollPane(historyTextArea);

		MessageConsole messageConsole = new MessageConsole(historyTextArea);
		messageConsole.redirectOut(null, System.out);
//		messageConsole.redirectErr(null, System.err);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		historyTextArea.setEditable(false);
//        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		this.add(scrollPane, BorderLayout.CENTER);
	}

}
