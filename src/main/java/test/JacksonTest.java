package test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JacksonTest {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        Round round = new Round();

        try {
            mapper.writeValue(new File("c:\\Users\\dimit\\Desktop\\rounds.json"), round);

            String jsonString = mapper.writeValueAsString(round);

            System.out.println(jsonString);

            String jsonString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(round);
            System.out.println(jsonString2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Round {
    long id;

    int[][] boardState;

    List<Integer> move;

    Round() {
        id = 1L;
        boardState = new int[][]{
            {2, 3, 0, 4, 1, 5, 0, 6, 8},
            {0, 8, 0, 2, 3, 6, 5, 1, 9},
            {1, 6, 0, 9, 8, 7, 2, 3, 4},
            {3, 1, 7, 0, 9, 4, 0, 2, 5},
            {4, 5, 8, 1, 2, 0, 6, 9, 7},
            {9, 2, 6, 0, 5, 8, 3, 0, 1},
            {0, 0, 0, 5, 0, 0, 1, 0, 2},
            {0, 0, 0, 8, 4, 2, 9, 0, 3},
            {5, 9, 2, 3, 7, 1, 4, 8, 6}
        };

        move = new ArrayList<Integer>();
        move.addAll(Arrays.asList(2, 0, 0));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int[][] getBoardState() {
        return boardState;
    }

    public void setBoardState(int[][] boardState) {
        this.boardState = boardState;
    }

    public List<Integer> getMove() {
        return move;
    }

    public void setMove(List<Integer> move) {
        this.move = move;
    }
}
