package dao;

import model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDaoImpl implements Dao<UserModel> {

    private List<UserModel> inMemoryUserDb;

    public InMemoryUserDaoImpl() {
        inMemoryUserDb = new ArrayList<>();

        UserModel admin = new UserModel(1, "admin");
        save(admin);
    }

    @Override
    public Iterable<? extends UserModel> findAll() {
        return inMemoryUserDb;
    }

    @Override
    public UserModel findById(long id) {
        return null;
    }

    @Override
    public <S extends UserModel> S save(S entity) {
        if (entity == null) throw new IllegalArgumentException("Invalid input");

        inMemoryUserDb.add(entity);
        return entity;
    }

    @Override
    public <S extends UserModel> S update(S entity) {
        return null;
    }

    @Override
    public int delete(UserModel entity) {
        return 0;
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existsById(long id) {
        return false;
    }
}
