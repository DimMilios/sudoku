package com.tei.view.game;

import javax.swing.*;

public class DefaultFieldState extends FieldState {
	public DefaultFieldState(TextField textField) {
		super(textField);
		update();
	}

	@Override
	public void update() {
		super.textField.setEditable(true);
		super.textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder(
				"TextField.border"));
	}
}
