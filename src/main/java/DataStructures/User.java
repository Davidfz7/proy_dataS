package DataStructures;

public class User {
    private int id;
    private String user;
    private String password;

    public User() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){

        return String.format("ID: %d, User: %s, password: %s", id, user, password);

    }
}
