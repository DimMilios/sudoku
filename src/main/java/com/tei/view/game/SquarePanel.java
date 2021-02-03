package com.tei.view.game;

import javax.swing.*;
import java.awt.*;

import static com.tei.model.SudokuConstants.SQUARE_SIZE;

// A 3x3 sub square
public class SquarePanel extends JPanel {

	public SquarePanel() {
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(SQUARE_SIZE, SQUARE_SIZE));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}
}
