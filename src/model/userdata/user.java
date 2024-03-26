package model.userdata;

public class user {
    private String displayName;

    public user(String displayName) {
        this.displayName = displayName;
    }

    public boolean isAdmin() {
        return false;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
