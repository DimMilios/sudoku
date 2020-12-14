package dao;


import models.Model;

public interface Dao<Model> {

    Iterable<? extends Model> findAll();

    Model findById(long id);

    Model save(Model value);

    Model update(Model value);

    int delete(Model value);

    int deleteById(long id);

    long count();

    boolean existsById(long id);
}
