package be.vdab.entiteiten;

public class User {
    private int id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(int id, String username, String password) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id + ", " + username + ", " + password;
    }
}
