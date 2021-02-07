package com.tei.view.game;

import com.tei.model.SudokuConstants;

import javax.swing.*;
import java.awt.*;

public class EmptyFieldState extends FieldState{

	public EmptyFieldState(TextField textField) {
		super(textField);
		update();
	}

	@Override
	public void update() {
		super.textField.setEditable(true);
		super.textField.setBorder(SudokuConstants.DEFAULT_TEXTFIELD_BORDER);
		super.textField.setText("");
		super.textField.setValue(null);
		super.textField.setForeground(Color.ORANGE);
	}
}
