package com.tei.controller;

import com.tei.model.BoardModelItem;

import java.time.LocalDateTime;

/**
 * The board controller interface contains methods describing operations related to the game board's
 * lifecycle.
 */
public interface BoardController {

	/**
	 * Initialize a board of given difficulty.
	 *
	 * @param difficulty the difficulty of the board
	 * @return the board item created
	 */
	BoardModelItem initializeBoard(String difficulty);

	/**
	 * Persist created board items to the database.
	 *
	 * @param item the item to be saved
	 */
	void persist(BoardModelItem item);

	/**
	 * Start new game by creating a new board.
	 */
	void startNewGame();

	/**
	 * Restart board.
	 */
	void restartBoard();

	/**
	 * Save game finish time when game has been completed.
	 *
	 * @param finishTime the finish time of the game
	 */
	void persistWithFinishTime(LocalDateTime finishTime);
}
