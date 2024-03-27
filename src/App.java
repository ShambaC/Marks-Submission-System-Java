import utility.DButil;
import view.LoginView;
import view.MarksMgmt;
import conf.config;

public class App {
    
    public static void main(String[] args) {
        DButil dbUtil = new DButil(config.DBaddress, config.DBUser, config.DBPassword, config.schemaLocation);
        dbUtil.dbInit();

         LoginView view = new LoginView();
         view.setVisible(true);

    //    MarksMgmt mm = new MarksMgmt();
    //    mm.setVisible(true);
    }
}
