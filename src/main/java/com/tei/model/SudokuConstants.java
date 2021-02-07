package com.tei.model;

import java.awt.*;

public class SudokuConstants {
	public static final String EASY = "Easy";
	public static final String NORMAL = "Normal";
	public static final String HARD = "Hard";
//	public static final int EASY_MISSING_DIGITS = 43;
	public static final int EASY_MISSING_DIGITS = 15;
	public static final int NORMAL_MISSING_DIGITS = 51;
	public static final int HARD_MISSING_DIGITS = 56;

	public static Color SELECTED = new Color(246, 250, 255);

	public static final String USER_PANEL = "User panel";
	public static final String GAME_PANEL = "Game panel";
	public static final String HISTORY_PANEL = "History panel";
	public static final String ABOUT_PANEL = "About panel";

	public static final int BOARD_SIZE = 9;
	public static final int SQUARE_SIZE = (int) Math.sqrt(BOARD_SIZE);

	public static final String GAME_MENU = "Game";
	public static final String OPTIONS_MENU = "Options";
	public static final String HELP_MENU = "Help";
	public static final String NEW_GAME = "New Game";
	public static final String RESTART = "Restart";
	public static final String EXIT = "Exit";
	public static final String HISTORY = "History";
	public static final String SHOW_HISTORY = "Show History";
	public static final String HIDE_HISTORY = "Hide History";
	public static final String ABOUT = "About";

	public static final String DEFAULT_STATE = "Default";
	public static final String DISABLED_STATE = "Disabled";
	public static final String INCORRECT_STATE = "Incorrect";
	public static final String EMPTY_STATE = "Empty";
}
