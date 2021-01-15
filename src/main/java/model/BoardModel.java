package model;

import observer.Observer;

import java.util.Arrays;

public class BoardModel extends Model {
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
        notifyObservers();
    }

    public void setField(int x, int y, int value) {
        this.state[x][y] = value;
        notifyObservers();
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
    public void subscribe(Observer o) {
        super.observers.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        super.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : super.observers){
            o.update(state);
        }
    }
}
