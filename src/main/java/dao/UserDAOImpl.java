package dao;

import database.MySQLConnection;
import model.UserModel;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

	private MySQLConnection mySqlConnection;

	public UserDAOImpl(MySQLConnection mySqlConnection) {
		this.mySqlConnection = mySqlConnection;
	}

	@Override
	public Iterable<UserModel> findAll() {
		return null;
	}

	@Override
	public UserModel findById(int id) {
		return null;
	}

	public UserModel findByUsername(String username) {
		String query = "SELECT * FROM USERS WHERE USERNAME=?";
		try (Connection connection = mySqlConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel save(UserModel entity) {
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
//					System.out.println(id);
					addedModel = new UserModel(id, entity.getUsername());
				}
				resultSet.close();
			}

		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
		return addedModel;
	}

}
