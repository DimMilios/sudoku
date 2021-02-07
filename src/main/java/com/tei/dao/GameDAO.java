package com.tei.dao;

import com.tei.model.GamePojo;
import com.tei.model.UserModel;

import java.sql.SQLException;

public interface GameDAO {

	boolean save(GamePojo entity) throws SQLException;

	boolean update(GamePojo entity) throws SQLException;

	UserModel findCurrentUser() throws SQLException;

}
