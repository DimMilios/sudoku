package com.tei.controller;

import com.tei.model.BoardModelItem;
import com.tei.view.game.TextField;

/**
 * The interface Field controller describes actions for dealing with each tile (field) of
 * the game board.
 */
public interface FieldController {
	/**
	 * Update a field with the value given.
	 *
	 * @param textField   the text field to be updated
	 * @param keyValue    the key value given by the user
	 * @param currentItem the current item representing board state
	 */
	void updateField(TextField textField, int keyValue, BoardModelItem currentItem);

	/**
	 * Reset text field state to default. Can be used when deleting/clearing field value.
	 *
	 * @param textField the text field to be reset
	 */
	void resetToDefault(TextField textField);
}
