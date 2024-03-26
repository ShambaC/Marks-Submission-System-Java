package model.userdata;

/**
 * Admin user
 */
public class admin extends user {
    public admin(String displayName) {
        super(displayName);
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
