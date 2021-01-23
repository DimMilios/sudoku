package view.game;

import javax.swing.*;
import java.awt.*;

public class IncorrectFieldState extends FieldState {
	public IncorrectFieldState(TextField textField) {
		super(textField);
		update();
	}

	@Override
	public void update() {
		super.textField.setBorder(
				BorderFactory.createLineBorder(new Color(200, 20, 20), 3));
	}
}
