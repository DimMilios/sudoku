package com.tei.model;

import com.tei.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class BoardModel extends Model {
    private final List<BoardModelItem> snapshots = new ArrayList<>();

    public BoardModel() {
        super(new ArrayList<>());
    }

    public BoardModel(int id) {
        super(id);
    }

    public void push(BoardModelItem item) {
        if (item != null) {
            this.snapshots.add(item);
            notifyObservers();
        }
    }

    public BoardModelItem first() {
        return this.snapshots.get(0);
    }

    public BoardModelItem last() {
        return this.snapshots.get(snapshots.size() - 1);
    }

    public boolean pop() {
        if (snapshots.size() <= 1 )
            return false;
        this.snapshots.remove(last());
        notifyObservers();
        return true;
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
            o.updateWith(this.last());
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        if (id > 0) {
            super.id = id;
        } else {
            throw new IllegalArgumentException("Negative ID value");
        }
    }

    @Override
    public String toString() {
        return "BoardModel{" +
                "snapshots=" + snapshots +
                ", id=" + id +
                '}';
    }
}
