package model;

import observer.Observable;
import observer.Observer;


public class UserModel extends Model implements Observable {
    private String username;

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
        notifyObservers();
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
    public void subscribe(Observer o) {
        super.observers.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        super.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : super.observers) {
            o.update(username);
        }
    }
}
