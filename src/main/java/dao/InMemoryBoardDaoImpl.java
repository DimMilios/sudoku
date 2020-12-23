package dao;

import models.BoardModel;
import test.SudokuGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryBoardDaoImpl implements Dao<BoardModel> {

    private static List<BoardModel> fakeDb;

    public InMemoryBoardDaoImpl() {
        fakeDb = new ArrayList<BoardModel>();

        int N = 9, K = 20;
        SudokuGenerator sudoku = new SudokuGenerator(N, K);
//        List<BoardModel> initialValues = Arrays.asList(
//                new BoardModel(1,"EASY", 0),
//                new BoardModel(2,"EASY", 0),
//                new BoardModel(3,"EASY", 0),
//                new BoardModel(4,"EASY", 0)
//        );

        List<BoardModel> initialValues = Arrays.asList(
                new BoardModel("EASY", 0),
                new BoardModel("EASY", 0),
                new BoardModel("EASY", 0),
                new BoardModel("EASY", 0)
        );

        for (BoardModel model : initialValues) {
            sudoku.fillValues();
            int[][] temp = new int[N][N];
            int[][] mat = sudoku.getMat();

            for (int i = 0; i < temp.length; i++)
                temp[i] = Arrays.copyOf(mat[i], mat[i].length);

            model.setState(temp);
            sudoku.emptyMat();
        }

        fakeDb.addAll(initialValues);
    }

    public Iterable<? extends BoardModel> findAll() {
        return fakeDb;
    }

    public BoardModel findById(long id) {
        for (BoardModel model : fakeDb) {
            if (model.getId() == id) {
                return model;
            }
        }
        return null;
    }

    public <S extends BoardModel> S save(S entity) {
        fakeDb.add(entity);
        return entity;
    }

    public <S extends BoardModel> S update(S entity) {
        return null;
    }

    public int delete(BoardModel entity) {
        return 0;
    }

    public int deleteById(long id) {
        return 0;
    }

    public long count() {
        return 0;
    }

    public boolean existsById(long id) {
        return false;
    }
}