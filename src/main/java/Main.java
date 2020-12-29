import com.formdev.flatlaf.FlatLightLaf;
import controller.StartGameController;
import dao.Dao;
import dao.InMemoryBoardDaoImpl;
import dao.InMemoryUserDaoImpl;
import model.BoardModel;
import model.UserModel;
import view.MainView;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        FlatLightLaf.install();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainView mainView = new MainView();
                Dao<BoardModel> boardDao = new InMemoryBoardDaoImpl();
                Dao<UserModel> userDao = new InMemoryUserDaoImpl();

                StartGameController startGameController = new StartGameController(boardDao, mainView);

                mainView.setVisible(true);
            }
        });
    }
}
