package model;

import java.util.Arrays;

public class BoardModel extends Model {
    private String difficulty;

    private int[][] state;

    private int currentTurn;

    public BoardModel(long id, String difficulty, int[][] state, int currentTurn) {
        super(id);
        this.difficulty = difficulty;
        this.state = state;
        this.currentTurn = currentTurn;
    }

//    public BoardModel(String difficulty, int[][] state, int currentTurn) {
//        this.difficulty = difficulty;
//        this.state = state;
//        this.currentTurn = currentTurn;
//    }
//
//    public BoardModel(String difficulty, int currentTurn) {
//        this.difficulty = difficulty;
//        this.currentTurn = currentTurn;
//    }

    public BoardModel(long id, String difficulty, int currentTurn) {
        super(id);
        this.difficulty = difficulty;
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
        this.state = state;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
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
}
