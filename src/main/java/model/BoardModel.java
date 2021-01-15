package model;

import observer.EventType;
import observer.Observable;
import observer.Observer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BoardModel extends Model implements Observable {
    private Map<Observer, EventType> observers = new HashMap<>();

    private String difficulty;

    private int[][] state;

    private int currentTurn;

    public BoardModel() {
        this.state = new int[9][9];
    }

    public BoardModel(String difficulty, int[][] state) {
        this.difficulty = difficulty;
        this.state = state;
    }

    public BoardModel(String difficulty, int[][] state, int currentTurn) {
        this.difficulty = difficulty;
        this.state = state;
        this.currentTurn = currentTurn;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        for (int i = 0; i < state.length; i++) {
            this.state[i] = Arrays.copyOf(state[i], state[i].length);
        }
        this.notify(EventType.BOARD_UPDATE);
        System.out.println("State updated");
    }

    public void setField(int x, int y, int value) {
        this.state[x][y] = value;
        this.notify(EventType.BOARD_UPDATE);
        System.out.println("State updated");
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String toString() {
        return "BoardModel{" +
                "difficulty='" + difficulty + '\'' +
                ", state=" + Arrays.deepToString(state)  +
                ", currentTurn=" + currentTurn +
                ", id=" + id +
                "}\n";
    }

    @Override
    public void subscribe(EventType eventType, Observer o) {
        this.observers.put(o, eventType);
    }

    @Override
    public void unsubscribe(EventType eventType, Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notify(EventType eventType) {
        for (Map.Entry<Observer, EventType> obs : observers.entrySet()) {
            obs.getKey().update(obs.getValue());
        }
    }
}
