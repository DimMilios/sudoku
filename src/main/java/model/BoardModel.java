package model;

import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class BoardModel extends Model {
    private final List<BoardModelItem> snapshots = new ArrayList<>();

    public BoardModel() {
        super(new ArrayList<>());
    }

    public void add(BoardModelItem item) {
        if (item != null) {
            this.snapshots.add(item);
//            System.out.println("Added item: " + item);
            notifyObservers();
        }
    }

    public BoardModelItem first() {
        return this.snapshots.get(0);
    }

    public BoardModelItem last() {
        return this.snapshots.get(snapshots.size() - 1);
    }

    public List<BoardModelItem> getSnapshots() {
        return snapshots;
    }

    public void clear() {
        this.snapshots.clear();
    }

    public static final class BoardModelItem {
        private final String difficulty;
        private final int[][] state;

        public BoardModelItem(String difficulty, int[][] state) {
            this.difficulty = difficulty;
            this.state = state;
        }

        @Override
        public String toString() {
            return "BoardModel{" +
                    "difficulty='" + difficulty + '\'' +
                    ", \nstate=\n" + printState(state);
        }

        public String printState(int[][] state) {
            String result = "";
            for (int i = 0; i < state.length; i++) {
                result += "[";
                for (int j = 0; j < state[i].length; j++) {
                    result = result.concat(state[i][j] + " ");
                }
                result += "]\n";
            }
            return result;
        }

        public int[][] getState() {
            return state;
        }

        public String getDifficulty() {
            return difficulty;
        }
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
        for (Observer o : this.observers){
            o.update(this.last());
        }
    }

    //    private String difficulty;
//    private int[][] state;
//
//    private int currentTurn;

//    public BoardModel() {
//        super(new ArrayList<>());
//        this.state = new int[9][9];
//    }
//
//    public BoardModel(String difficulty, int[][] state) {
//        super(new ArrayList<>());
//        this.difficulty = difficulty;
//        this.state = state;
//    }

//    public String getDifficulty() {
//        return difficulty;
//    }
//
//    public void setDifficulty(String difficulty) {
//        this.difficulty = difficulty;
//    }
//
//    public int[][] getState() {
//        return state;
//    }
//
//    public void setState(int[][] state) {
//        for (int i = 0; i < state.length; i++) {
//            this.state[i] = Arrays.copyOf(state[i], state[i].length);
//        }
//        notifyObservers();
//    }
//
//    public void setField(int x, int y, int value) {
//        this.state[x][y] = value;
//        notifyObservers();
//    }
//
//    public int getCurrentTurn() {
//        return currentTurn;
//    }
//
//    public void setCurrentTurn(int currentTurn) {
//        this.currentTurn = currentTurn;
//    }

    @Override
    public long getId() {
        return 0;
    }

//    @Override
//    public String toString() {
//        return "BoardModel{" +
//                "difficulty='" + difficulty + '\'' +
//                ", state=" + printState(state)  +
//                ", currentTurn=" + currentTurn +
//                ", id=" + id +
//                "}\n";
//    }
}
