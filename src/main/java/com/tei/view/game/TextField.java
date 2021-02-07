package com.tei.view.game;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

import static com.tei.model.SudokuConstants.*;

public class TextField extends JFormattedTextField {

	private Position position;
	private int number;
	private FieldState fieldState;

	public static MaskFormatter addFormatter() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("#");
			mask.setInvalidCharacters("0");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mask;
	}

	public TextField() {
		super(addFormatter());
		init();
	}

	public TextField(Position position, int number) {
		super(addFormatter());
		this.position = position;
		this.number = number;
		init();
		initState();
	}

	private void init() {
		this.setFont(new Font("Arial", Font.BOLD, 26));
		this.setCaretColor(Color.WHITE);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void initState() {
		if (number >= 1 && number <= 9) {
			this.fieldState = FieldStateFactory.create(DISABLED_STATE, this);
			this.setValue(number);
		} else {
		  this.fieldState = FieldStateFactory.create(DEFAULT_STATE, this);
		}
	}

	public FieldState getFieldState() {
		return fieldState;
	}

	public void setFieldState(FieldState fieldState) {
		this.fieldState = fieldState;
	}

	public Position getPosition() {
		return position;
	}
}
