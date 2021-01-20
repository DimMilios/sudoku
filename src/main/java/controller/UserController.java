package controller;

import dao.Dao;
import model.UserModel;
import view.MainView;

import javax.swing.*;

public class UserController {
//    private Dao<UserModel> userDao;
    private MainView mainView;

    public UserController(MainView mainView) {
        this.mainView = mainView;
    }

    public void update(String username) {
        UserModel.getInstance()
                 .setUsername(validate(username));
    }

    private String validate(String username) {
        while (username.trim().length() < 3) {
            username = JOptionPane.showInputDialog(mainView,
                                                   "Username missing or too short",
                                                   "Error",
                                                   JOptionPane.ERROR_MESSAGE);
        }
        return username;
    }
}
