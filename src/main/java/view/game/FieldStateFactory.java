package view.game;

import static model.SudokuConstants.*;

public class FieldStateFactory {

	public static FieldState create(String stateType, TextField textField) {
		switch (stateType) {
			case DISABLED_STATE:
				return new DisabledFieldState(textField);
			case INCORRECT_STATE:
				return new IncorrectFieldState(textField);
			default:
				return new DefaultFieldState(textField);
		}
	}
}
