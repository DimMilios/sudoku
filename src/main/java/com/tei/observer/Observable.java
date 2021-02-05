package com.tei.observer;


/**
 * The interface Observable for using Observer design pattern. Classes implementing
 * the Observer interface can subscribe to an Observable so that they can be informed
 * when its state changes.
 */
public interface Observable {
	/**
	 * Subscribes an observer to watch for this observable's state changes.
	 *
	 * @param o the observer to be added to an observable's observer list
	 */
	void subscribe(Observer o);

	/**
	 * Unsubscribe from this observable.
	 *
	 * @param o the observer to be removed
	 */
	void unsubscribe(Observer o);

	/**
	 * Notify all subscribed observers when the observable state changes.
	 */
	void notifyObservers();
}
