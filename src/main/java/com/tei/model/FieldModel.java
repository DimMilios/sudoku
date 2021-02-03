package com.tei.model;

import java.time.LocalDateTime;

public class FieldModel {
	private final int x;
	private final int y;
	private final int value;
	private LocalDateTime moveTime;

	public FieldModel(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "FieldModel {" +
				"x=" + x +
				", y=" + y +
				", value=" + value +
				'}';
	}
}
