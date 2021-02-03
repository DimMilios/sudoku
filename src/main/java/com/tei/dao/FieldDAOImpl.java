package com.tei.dao;

import com.tei.database.MySQLConnection;
import com.tei.model.FieldModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FieldDAOImpl implements FieldDAO {

	private final MySQLConnection mySQLConnection;

	public FieldDAOImpl(MySQLConnection mySQLConnection) {
		this.mySQLConnection = mySQLConnection;
	}

	@Override
	public boolean save(FieldModel entity) {
		String query = "INSERT INTO MOVES(x, y, value) VALUES (?, ?, ?);";
		try (Connection connection = mySQLConnection.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, entity.getX());
			pstmt.setInt(2, entity.getY());
			pstmt.setInt(3, entity.getValue());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return false;
	}
}
