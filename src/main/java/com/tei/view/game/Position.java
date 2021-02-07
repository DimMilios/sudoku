package com.tei.view.game;

public final class Position {

	private final int x;
	private final int y;
	private final int squareX;
	private final int squareY;

	public Position(int x, int y, int squareX, int squareY) {
		this.x = x;
		this.y = y;
		this.squareX = squareX;
		this.squareY = squareY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSquareX() {
		return squareX;
	}

	public int getSquareY() {
		return squareY;
	}

	@Override
	public String toString() {
		return "Position {" +
				"x=" + x +
				", y=" + y +
				", squareX=" + squareX +
				", squareY=" + squareY +
				'}';
	}
}
