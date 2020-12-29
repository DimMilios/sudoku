package dao;


public interface Dao<T> {

    Iterable<? extends T> findAll();

    T findById(long id);

    <S extends T> S save(S entity);

    <S extends T> S update(S entity);

    /**
     * Delete the given entity
     * defaults to -1 (failure), any other int means success
     * @param entity - the entity to be deleted
     * @return int status
     */
    default int delete(T entity) {
        return -1;
    }

    /**
     * Delete the entity by its id
     * default return value is -1 to show failure
     * any other int value means operation finished successfully
     * @param id - id of the entity to be deleted
     * @return int status
     */
    default int deleteById(long id) {
        return -1;
    }

    long count();

    boolean existsById(long id);
}
