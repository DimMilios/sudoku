package view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TextField extends JFormattedTextField {

	private int gridX;
	private int gridY;
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

	public TextField(int gridX, int gridY, int value) {
		super(addFormatter());
		this.gridX = gridX;
		this.gridY = gridY;
		init();
		initState(value);
	}

	private void init() {
		this.setFont(new Font("Arial", Font.BOLD, 26));
		this.setCaretColor(Color.WHITE);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void initState(int value) {
		if (value >= 1 && value <= 9) {
			this.fieldState = new DisabledFieldState(this);
			this.setText(String.valueOf(value));
		} else {
		  this.fieldState = new DefaultFieldState(this);
		}
	}

	public int getGridX() {
		return gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public FieldState getFieldState() {
		return fieldState;
	}

	public void setFieldState(FieldState fieldState) {
		this.fieldState = fieldState;
	}
}
