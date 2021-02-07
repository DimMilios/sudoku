package com.tei.view.game;

import com.tei.model.SudokuConstants;

import javax.swing.*;
import java.awt.*;

public class DefaultFieldState extends FieldState {
	public DefaultFieldState(TextField textField) {
		super(textField);
		update();
	}

	@Override
	public void update() {
		super.textField.setEditable(true);
		super.textField.setBorder(SudokuConstants.DEFAULT_TEXTFIELD_BORDER);
		super.textField.setFont(new Font("Arial", Font.BOLD, 32));
		super.textField.setForeground(new Color(20, 20, 190));
	}
}
