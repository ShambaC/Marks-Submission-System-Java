import utility.DButil;
import view.LoginView;
import conf.config;

/**
 * Runner class for the application
 * <p> It initializes the Database and then runs the UI
 */
public class App {
    
    public static void main(String[] args) {
        DButil dbUtil = new DButil(config.DBaddress, config.DBUser, config.DBPassword, config.schemaLocation);
        dbUtil.dbInit();

         LoginView view = new LoginView();
         view.setVisible(true);
    }
}