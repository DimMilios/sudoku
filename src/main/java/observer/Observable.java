package observer;

public interface Observable {
	void subscribe(EventType eventType, Observer o);

	void unsubscribe(EventType eventType, Observer o);

	void notify(EventType eventType);
}
