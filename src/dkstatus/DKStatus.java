package dkstatus;

import dkstatus.cookies.ChromeCookieProvider;
import dkstatus.cookies.CookieStoreManager;
import dkstatus.requests.AnnouncesRequest;
import dkstatus.ui.MainWindow;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Johny
 */
public class DKStatus {

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
        
        CookieStoreManager.setProvider(new ChromeCookieProvider());
        
        try {
            String res = AnnouncesRequest.getResult();
            window.setResponseText(res);
        } catch (IOException ex) {
            Logger.getLogger(DKStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
