package Lab8;

import java.io.Serializable;

public class User implements Serializable {
    private String user, pass;

    public User(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public User() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
