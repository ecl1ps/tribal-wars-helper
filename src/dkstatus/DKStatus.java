package dkstatus;

import dkstatus.cookies.ChromeDataProvider;
import dkstatus.cookies.BrowserManager;
import dkstatus.requests.BasicDataRequest;
import dkstatus.ui.MainWindow;
import dkstatus.world.World;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Johny
 */
public class DKStatus {
    
    private static MainWindow window;
    private static final World world = new World();
    private static final Timer t = new Timer();;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new MainWindow();
                window.setVisible(true);
            }
        });
        
        BrowserManager.setProvider(new ChromeDataProvider());
        
        UpdateData();
    }

    public static void UpdateData() {
        try {
            BasicDataRequest.updateData(world);
            world.ProcessUpdate();
        } catch (IOException ex) {
            Logger.getLogger(DKStatus.class.getName()).log(Level.SEVERE, null, ex);
        }

        t.schedule(new UpdateTask(), world.getNexUpdateTime());
        window.updateWindow(world);
    }

}
