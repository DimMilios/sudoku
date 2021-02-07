package com.tei.dao;

import com.tei.model.UserModel;

import java.sql.SQLException;

public interface UserDAO {

	UserModel save(UserModel entity) throws SQLException;
}
