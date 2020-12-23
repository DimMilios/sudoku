package models;

public abstract class Model {

    protected static long id;

//    public Model(long id) {
//        Model.id = id;
//    }

    public Model() {
        id++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Negative id");
        }

        Model.id = id;
    }
}