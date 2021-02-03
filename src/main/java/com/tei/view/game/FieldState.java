package com.tei.view.game;

public abstract class FieldState {
	protected TextField textField;

	public FieldState(TextField textField) {
		this.textField = textField;
	}

	public abstract void update();
}
