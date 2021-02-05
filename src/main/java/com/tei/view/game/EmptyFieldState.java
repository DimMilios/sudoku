package com.tei.view.game;

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
		super.textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder(
				"TextField.border"));
		super.textField.setText("");
		super.textField.setValue(null);
		super.textField.setForeground(Color.ORANGE);
	}
}
