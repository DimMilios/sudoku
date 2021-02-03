package com.tei.dao;

import com.tei.database.MySQLConnection;
import com.tei.model.BoardModel;

import java.sql.*;

import static com.tei.model.BoardModel.*;

public class BoardDAOImpl implements BoardDAO {

	private MySQLConnection mySqlConnection;
	private BoardModel boardModel;

	public BoardDAOImpl(MySQLConnection mySqlConnection, BoardModel boardModel) {
		this.mySqlConnection = mySqlConnection;
		this.boardModel = boardModel;
	}

	@Override
	public Iterable<BoardModelItem> findAll() {
		return null;
	}

	@Override
	public BoardModelItem findById(int id) {
		return null;
	}

	@Override
	public BoardModelItem save(BoardModelItem entity) {
		String query = "INSERT INTO BOARDS(difficulty) VALUES(?)";

		try (Connection connection = mySqlConnection.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query,
																   Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, entity.getDifficulty());
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					boardModel.setId(id);
				}
				rs.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}
}
