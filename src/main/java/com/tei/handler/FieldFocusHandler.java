package com.tei.handler;

import com.tei.model.SudokuConstants;
import com.tei.view.game.BoardPanel;
import com.tei.view.game.FieldStateFactory;
import com.tei.view.game.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static com.tei.model.SudokuConstants.SELECTED;
import static com.tei.model.SudokuConstants.SQUARE_SIZE;

public class FieldFocusHandler implements FocusListener {
	private final BoardPanel boardPanel;

	public FieldFocusHandler(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
	}

	@Override
	public void focusGained(FocusEvent e) {
		TextField focused = (TextField) e.getSource();
		focused.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 3));

		int x = focused.getPosition().getX();
		int y = focused.getPosition().getY();
		int squareX = focused.getPosition().getSquareX();
		int squareY = focused.getPosition().getSquareY();

		int i1 = x - x % SQUARE_SIZE;
		int i2 = y - y % SQUARE_SIZE;
		for (int i = 0; i < boardPanel.getTextFields().length; i++) {
			for (int j = 0; j < boardPanel.getTextFields()[i].length; j++) {
				if (i == x || j == y) {
					boardPanel.getTextFields()[i][j].setBackground(SELECTED);
				}

				if (i1 == squareX || i2 == squareY) {
					boardPanel.getTextFields()[i % 3 + i1][j % 3 + i2].setBackground(SELECTED);
				}
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		TextField focused = (TextField) e.getSource();
		focused.setBorder(SudokuConstants.DEFAULT_TEXTFIELD_BORDER);

		for (int i = 0; i < boardPanel.getTextFields().length; i++) {
			for (int j = 0; j < boardPanel.getTextFields()[i].length; j++) {
				if (boardPanel.getTextFields()[i][j].getBackground().equals(SELECTED)) {
					boardPanel.getTextFields()[i][j].setBackground(new Color(255, 255, 255));
				}
			}
		}
	}
}
