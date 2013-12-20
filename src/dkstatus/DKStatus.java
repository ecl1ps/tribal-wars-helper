package dkstatus;

import dkstatus.ui.WindowManager;
import dkstatus.world.World;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 *
 * @author Johny
 */
public class DKStatus {
    
    private static World world = new World();
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    
    public static void main(String[] args) {
        
        WindowManager.initialize();

        updateData();
    }

    private static void updateData() {
        executor.execute(new UpdateTask(executor, world));
    }

    public static void refreshUpdate() {
        executor.shutdownNow();
        executor = Executors.newScheduledThreadPool(2);
        world = new World();
        updateData();
    }
}
