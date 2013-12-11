package dkstatus;

import dkstatus.cookies.ChromeDataProvider;
import dkstatus.cookies.BrowserManager;
import dkstatus.requests.BasicDataRequest;
import dkstatus.ui.MainWindow;
import dkstatus.world.World;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Johny
 */
public class DKStatus {

    public static void main(String[] args) {
        World world = new World();
        
        MainWindow window = new MainWindow();
        window.setVisible(true);
        
        BrowserManager.setProvider(new ChromeDataProvider());
        try {
            BasicDataRequest.updateData(world);
        } catch (IOException ex) {
            Logger.getLogger(DKStatus.class.getName()).log(Level.SEVERE, null, ex);
        }

        window.updateWindow(world);
    }
    
}
