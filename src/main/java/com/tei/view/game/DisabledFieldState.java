package com.tei.view.game;

import java.awt.*;

public class DisabledFieldState extends FieldState {
	public DisabledFieldState(TextField textField) {
		super(textField);
		update();
	}

	@Override
	public void update() {
		super.textField.setEditable(false);
		super.textField.setOpaque(true);
		super.textField.setBackground(new Color(255, 255, 255));
		super.textField.setFont(new Font("Arial", Font.BOLD, 32));
	}

}
