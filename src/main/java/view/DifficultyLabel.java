package view;

import model.BoardModel;
import observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DifficultyLabel extends JLabel implements Observer {

	private BoardModel boardModel;

	public DifficultyLabel(BoardModel boardModel) {
		this.boardModel = boardModel;
		this.boardModel.subscribe(this);
		this.setFont(new Font("Arial", Font.BOLD, 26));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
	}

	@Override
	public void update(Object state) {
		if (boardModel.getDifficulty() != null) {
			this.setText("Difficulty: " + boardModel.getDifficulty());
		}
	}
}