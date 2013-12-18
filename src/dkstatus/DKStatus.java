package dkstatus;

import dkstatus.cookies.ChromeDataProvider;
import dkstatus.cookies.BrowserManager;
import dkstatus.requests.BasicDataRequest;
import dkstatus.requests.VillageListRequest;
import dkstatus.ui.MainWindow;
import dkstatus.world.World;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 *
 * @author Johny
 */
public class DKStatus {
    
    private static volatile MainWindow window;
    private static World world = new World();
    private static final Timer t = new Timer();;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new MainWindow();
                window.setVisible(true);
            }
        });
        
        UpdateData();        
    }

    public static void UpdateData() {
        try {
            if (!world.getPlayer().isLoggedIn()) {
                BrowserManager.refreshCookies();
                new VillageListRequest().updateData(world);
            }
            
            new BasicDataRequest().updateData(world);
        } catch (UnknownHostException ex) {
            // without internet connection
            world.getPlayer().setName("Offline");
        } catch (IOException ex) {
            Logger.getLogger(DKStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        world.ProcessUpdate();

        while (window == null) ; // bussy wait till window is created

        t.schedule(new UpdateTask(), world.getNexUpdateTime());
        window.updateWindow(world);
    }

    public static void RefreshUpdate() {
        world = new World();
        UpdateData();
    }
}
