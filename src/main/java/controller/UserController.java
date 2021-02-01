package controller;

import dao.UserDAO;
import model.UserModel;
import view.MainView;

import javax.swing.*;

public class UserController {

	private final MainView mainView;
	private final UserDAO userDao;
	private final UserModel userModel;

	public UserController(MainView mainView, UserDAO userDao, UserModel userModel) {
		this.mainView = mainView;
		this.userDao = userDao;
		this.userModel = userModel;
	}

	public void update(String username) {
		userModel.setUsername(validate(username));
		UserModel addedModel = userDao.save(userModel);
		if (addedModel != null) {
			userModel.setId(addedModel.getId());
//			System.out.println(userModel);
		}
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
