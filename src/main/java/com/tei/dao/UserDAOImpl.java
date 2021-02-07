package com.tei.dao;

import com.tei.database.MySQLConnection;
import com.tei.model.UserModel;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

	private MySQLConnection mySqlConnection;

	public UserDAOImpl(MySQLConnection mySqlConnection) {
		this.mySqlConnection = mySqlConnection;
	}

	@Override
	public UserModel save(UserModel entity) throws SQLException {
		String insertUser = "INSERT INTO USERS(username) VALUES (?)";
		UserModel addedModel = null;

		try (Connection connection = mySqlConnection.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(insertUser,
																   Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, entity.getUsername());
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				ResultSet resultSet = pstmt.getGeneratedKeys();
				while (resultSet.next()) {
					int id = resultSet.getInt(1);
					addedModel = new UserModel(id, entity.getUsername());
				}
				resultSet.close();
			}

		} catch (SQLException throwable) {
			throw throwable;
		}
		return addedModel;
	}

}
