package model;

public class UserModel extends Model {
    private String username;

    public UserModel(long id, String username) {
        super(id);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                "username='" + username + '\'' +
                '}';
    }
}
