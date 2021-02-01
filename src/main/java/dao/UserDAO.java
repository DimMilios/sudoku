package dao;

import model.UserModel;

public interface UserDAO {

	Iterable<UserModel> findAll();

	UserModel findById(int id);

	UserModel save(UserModel entity);
}
