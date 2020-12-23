import dao.Dao;
import dao.InMemoryBoardDaoImpl;
import models.BoardModel;

public class Main {

    public static void main(String[] args) {
        Dao<BoardModel> dao = new InMemoryBoardDaoImpl();
//        System.out.println(dao.findAll());

//        System.out.println(dao.findById(2L));

//        dao.save(new BoardModel(5L,"EASY", 0));
        dao.save(new BoardModel("EASY", 0));
        System.out.println(dao.findAll());
    }
}
