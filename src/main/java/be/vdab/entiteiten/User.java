package be.vdab.entiteiten;

public class User {
    private String username;
    private String pasword;

    public User(String username, String pasword) {
        this.setUsername(username);
        this.setPasword(pasword);
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
}
