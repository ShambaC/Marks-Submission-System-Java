package model.userdata;

public class admin {
    private String email;
    private String userName;

    public admin(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public String getMail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
