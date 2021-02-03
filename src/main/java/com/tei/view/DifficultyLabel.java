package com.tei.view;

import com.tei.model.BoardModel;
import com.tei.observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DifficultyLabel extends JLabel implements Observer {

	private final BoardModel boardModel;

	public DifficultyLabel(BoardModel boardModel) {
		this.boardModel = boardModel;
		this.boardModel.subscribe(this);
		this.setFont(new Font("Arial", Font.BOLD, 26));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
	}

	@Override
	public void update(Object state) {
		if (boardModel.last().getDifficulty() != null) {
			this.setText("Difficulty: " + boardModel.last().getDifficulty());
		}
	}
}
