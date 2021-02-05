package com.tei.model;

import java.time.LocalDateTime;

public class FieldPojo {
	private final int x;
	private final int y;
	private final int value;
	private LocalDateTime moveTime;

	public FieldPojo(int x, int y, int value) {
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
		return "FieldPojo {" +
				"x=" + x +
				", y=" + y +
				", value=" + value +
				'}';
	}
}
