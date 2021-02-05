package com.tei.controller;

import com.tei.dao.UserDAO;
import com.tei.model.UserModel;
import com.tei.view.MainView;

import javax.swing.*;
import java.sql.SQLException;

public class UserControllerImpl implements UserController {

	private final MainView mainView;
	private final UserDAO userDao;
	private final UserModel userModel;

	public UserControllerImpl(MainView mainView, UserDAO userDao, UserModel userModel) {
		this.mainView = mainView;
		this.userDao = userDao;
		this.userModel = userModel;
	}

	@Override
	public void updateUserModelWith(String username) {
		userModel.setUsername(validate(username));
		try {
			UserModel addedModel = userDao.save(userModel);
			if (addedModel != null) {
				userModel.setId(addedModel.getId());
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
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
