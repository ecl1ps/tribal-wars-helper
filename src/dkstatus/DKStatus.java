package dkstatus;

import dkstatus.requests.BasicDataRequest;
import dkstatus.ui.WindowManager;


/**
 *
 * @author Johny
 */
public class DKStatus {
    
    public static void main(String[] args) throws InterruptedException {

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
}
