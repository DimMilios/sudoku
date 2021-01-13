package model;

public class UserModel extends Model {
    private String username;

    private static UserModel instance = null;

    private UserModel() {
    }

    public static UserModel getInstance() {
        if (instance == null) {
            instance = new UserModel();
        }
        return instance;
    }

    public UserModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                "username='" + username + '\'' +
                '}';
    }
}
