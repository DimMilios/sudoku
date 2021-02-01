package dao;

import model.GamePojo;
import model.UserModel;

import java.sql.SQLException;

public interface GameDAO {

	Iterable<GamePojo> findAll();

	GamePojo findById(int id);

	boolean save(GamePojo entity) throws SQLException;

	boolean update(GamePojo entity) throws SQLException;

	UserModel findCurrentUser() throws SQLException;

}
