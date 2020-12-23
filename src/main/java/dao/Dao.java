package dao;


public interface Dao<T> {

    Iterable<? extends T> findAll();

    T findById(long id);

    <S extends T> S save(S entity);

    <S extends T> S update(S entity);

    int delete(T entity);

    int deleteById(long id);

    long count();

    boolean existsById(long id);
}
