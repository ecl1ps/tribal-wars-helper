package dkstatus;

import dkstatus.browser.BrowserManager;
import dkstatus.browser.IBrowserDataProvider;
import dkstatus.requests.BasicDataRequest;
import dkstatus.ui.WindowManager;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Johny
 */
public class DKStatus {
    
    public static void main(String[] args) throws InterruptedException {
        
        Config.init();
        
        try {
            BrowserManager.setProvider((IBrowserDataProvider)Class.forName(Config.getStringProperty(Config.ConfigKey.BROWSER_CLASS)).newInstance());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DKStatus.class.getName()).log(Level.WARNING, "Specified browser provider not found, using default", ex);
        }
        
        WindowManager.initialize();

        updateData();
    }

    private static void updateData() {
        WebRequestService.scheduleTask(new BasicDataRequest(), 0);
    }

    public static void refreshUpdate() {
        WebRequestService.restartService();
        updateData();
    }

    public static void resetAlert() {
        WebRequestService.getWorld().resetAlert();
    }
}
