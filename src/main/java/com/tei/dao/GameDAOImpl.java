package com.tei.dao;

import com.tei.database.MySQLConnection;
import com.tei.model.BoardModel;
import com.tei.model.GamePojo;
import com.tei.model.UserModel;

import java.sql.*;

public class GameDAOImpl implements GameDAO {

	private final MySQLConnection mySqlConnection;
	private final BoardModel boardModel;

	public GameDAOImpl(MySQLConnection connection,
					   BoardModel boardModel) {
		this.mySqlConnection = connection;
		this.boardModel = boardModel;
	}

	@Override
	public Iterable<GamePojo> findAll() {
		return null;
	}

	@Override
	public GamePojo findById(int id) {
		return null;
	}

	@Override
	public boolean save(GamePojo entity) throws SQLException {
		String query = "INSERT INTO GAMES(user_id, board_id, start_time) VALUES (?, ?, ?)";
		try (Connection connection = mySqlConnection.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, entity.getUserId());
			pstmt.setInt(2, entity.getBoardId());
			pstmt.setTimestamp(3, Timestamp.valueOf(entity.getStartTime()));

			return pstmt.executeUpdate() > 0;
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		throw new SQLException("Save failed");
	}

	@Override
	public boolean update(GamePojo entity) throws SQLException {
		String query = "UPDATE GAMES g SET g.finish_time=? WHERE g.user_id=? AND g.board_id=?;";
		try (Connection connection = mySqlConnection.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setTimestamp(1, Timestamp.valueOf(entity.getFinishTime()));
			pstmt.setInt(2, entity.getUserId());
			pstmt.setInt(3, entity.getBoardId());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public UserModel findCurrentUser() throws SQLException {
		String query = "SELECT * FROM USERS u WHERE u.id = (SELECT MAX(id) FROM USERS);";
		try (Connection connection = mySqlConnection.getConnection();
			 Statement stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {
			if (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				return new UserModel(id, username);
			}
		}
		throw new SQLException("User not found");
	}

//	private GamePojo findByUserAndBoard(int userId, int boardId) {
//		String query = "SELECT g.user_id, g.board_id, g.start_time " +
//				"FROM GAMES g INNER JOIN USERS u ON u.id = g.user_id" +
//				" INNER JOIN BOARDS b ON b.id = g.board_id " +
//				"WHERE u.id=? AND b.id=?;";
//
//		try (Connection connection = mySqlConnection.getConnection();
//			 PreparedStatement pstmt = connection.prepareStatement(query)) {
//			pstmt.setInt(1, userId);
//			pstmt.setInt(2, boardId);
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					Timestamp startTime = rs.getTimestamp("start_time");
//					return new GamePojo(userId, boardId, startTime.toLocalDateTime());
//				}
//			}
//
//		} catch (SQLException exception) {
//			exception.printStackTrace();
//		}
//		return null;
//	}
}