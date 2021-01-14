package observer;

public interface Observable<T> {
	void subscribe(EventType eventType, Observer o);

	void unsubscribe(EventType eventType, Observer o);

	void notify(EventType eventType, T item);
}
