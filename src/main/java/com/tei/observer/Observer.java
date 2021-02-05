package com.tei.observer;

/**
 * The interface Observer. A class implementing this interface can subscribe
 * to an Observable to be informed when its inner state changes.
 */
public interface Observer {

	/**
	 * Update with new observable state.
	 *
	 * @param state the state of the observable that was changed
	 */
	void updateWith(Object state);
}
