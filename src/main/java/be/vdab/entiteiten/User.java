package be.vdab.entiteiten;

public class User {
    private int id;
    private String username;
    private String pasword;

    public User(int id, String username, String pasword) {
        this.setId(id);
        this.setUsername(username);
        this.setPasword(pasword);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    @Override
    public String toString() {
        return id + ", " + username + ", " + pasword;
    }
}
