package com.tei.dao;

import com.tei.database.MySQLConnection;
import com.tei.model.BoardModel;
import com.tei.model.FieldPojo;
import com.tei.model.UserModel;

import java.sql.*;

public class FieldDAOImpl implements FieldDAO {

	private final MySQLConnection mySQLConnection;
	private final BoardModel boardModel;
	private final UserModel userModel;

	public FieldDAOImpl(MySQLConnection mySQLConnection,
						BoardModel boardModel,
						UserModel userModel) {
		this.mySQLConnection = mySQLConnection;
		this.boardModel = boardModel;
		this.userModel = userModel;
	}

	@Override
	public boolean save(FieldPojo entity) {
		String query = "INSERT INTO MOVES(x, y, value) VALUES (?, ?, ?);";
		try (Connection connection = mySQLConnection.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query,
																   Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, entity.getX());
			pstmt.setInt(2, entity.getY());
			pstmt.setInt(3, entity.getValue());

			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				ResultSet resultSet = pstmt.getGeneratedKeys();
				while (resultSet.next()) {
					int moveId = resultSet.getInt(1);
					saveGameMove(moveId);
				}
				resultSet.close();
			}
			return true;
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return false;
	}

	private void saveGameMove(int moveId) throws SQLException {
		String insertGameMove = "INSERT INTO GAME_MOVE(user_id, board_id, move_id) VALUES (?, ?, ?);";
		try (Connection connection = mySQLConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(insertGameMove)) {
			preparedStatement.setInt(1, userModel.getId());
			preparedStatement.setInt(2, boardModel.getId());
			preparedStatement.setInt(3, moveId);
			preparedStatement.executeUpdate();
		}
	}
}
