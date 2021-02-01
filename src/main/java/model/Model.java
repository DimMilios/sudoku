package model;

import observer.Observable;
import observer.Observer;

import java.util.List;

public abstract class Model implements Observable {

    protected int id;
    protected List<Observer> observers;

    public Model(List<Observer> observers) {
        this.observers = observers;
    }

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    public abstract int getId();

    public abstract void setId(int id);
}
