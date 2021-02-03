package com.tei.dao;

import com.tei.model.UserModel;

import java.sql.SQLException;

public interface UserDAO {

	Iterable<UserModel> findAll();

	UserModel findById(int id);

	UserModel findByUsername(String username);

	UserModel save(UserModel entity) throws SQLException;
}
