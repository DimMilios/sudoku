package model;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Model implements Observable {

    protected long id;
    protected List<Observer> observers = new ArrayList<>();

    public Model() {
    }

    public abstract long getId();
}
