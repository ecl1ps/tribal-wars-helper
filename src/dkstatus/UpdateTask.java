
package dkstatus;

import dkstatus.cookies.BrowserManager;
import dkstatus.requests.BasicDataRequest;
import dkstatus.requests.VillageListRequest;
import dkstatus.ui.WindowManager;
import dkstatus.world.World;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johny
 */
public class UpdateTask extends TimerTask {

    private final World world;
    private final ScheduledExecutorService executor;
    
    public UpdateTask(ScheduledExecutorService executor, World world) {
        this.world = world;
        this.executor = executor;
    }
    
    @Override
    public void run() {
        updateData();
    }
    
    private void updateData() {
        world.beforeUpdate();
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
        
        world.afterUpdate();

        while (!WindowManager.isInitialized()) ; // bussy wait till window is created

        executor.schedule(new UpdateTask(executor, world), world.getNexUpdateTime(), TimeUnit.MILLISECONDS);
        WindowManager.getWindow().updateWindow(world);
    }    
}
