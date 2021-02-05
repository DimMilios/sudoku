package com.tei.model;

import com.tei.observer.Observer;

import java.util.ArrayList;


public class UserModel extends Model {
    private String username;

    public UserModel() {
        super(new ArrayList<>());
    }

    public UserModel(int id, String username) {
        super(id);
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
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        super.id = id;
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
        this.observers.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.updateWith(username);
        }
    }
}
