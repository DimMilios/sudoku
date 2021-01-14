package model;

import observer.EventType;
import observer.Observable;
import observer.Observer;

import java.util.HashMap;
import java.util.Map;


public class UserModel extends Model implements Observable<String> {
    private String username;
    private Map<Observer, EventType> observers = new HashMap<>();

    private static UserModel instance = null;

    private UserModel() {
    }

    public static UserModel getInstance() {
        if (instance == null) {
            instance = new UserModel();
        }
        return instance;
    }

    public UserModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.notify(EventType.USERNAME_INSERT, username);
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                "username='" + username + '\'' +
                '}';
    }

    @Override
    public void subscribe(EventType eventType, Observer o) {
        observers.put(o, eventType);
    }

    @Override
    public void unsubscribe(EventType eventType, Observer o) {
        observers.remove(o);
    }

    @Override
    public void notify(EventType eventType, String item) {
        for (Map.Entry<Observer, EventType> obs : observers.entrySet()) {
            obs.getKey().update(obs.getValue());
        }
    }
}
