package view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TextField extends JFormattedTextField {

	private int gridX;
	private int gridY;

	public static TextField createTextField(int gridX, int gridY) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("#");
			mask.setInvalidCharacters("0");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return new TextField(mask, gridX, gridY);
	}

	private TextField(AbstractFormatter formatter, int gridX, int gridY) {
		super(formatter);
		this.gridX = gridX;
		this.gridY = gridY;
		init();
	}

	private void init() {
		this.setFont(new Font("Arial", Font.BOLD, 26));
		this.setCaretColor(Color.WHITE);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}
}
