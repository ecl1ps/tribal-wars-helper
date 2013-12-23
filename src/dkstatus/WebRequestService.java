
package dkstatus;

import dkstatus.requests.IUpdateRequest;
import dkstatus.world.World;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Johny
 */
public class WebRequestService {
    
    private static final Random rand = new Random(System.currentTimeMillis());
    private static final int THREAD_POOL_SIZE = 10;
    private static World world = new World();
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
 
    public static void scheduleTask(IUpdateRequest request, long delay) {
        executor.schedule(new UpdateTask(request, world), delay, TimeUnit.MILLISECONDS);
    }
            
    public static void restartService() {
        executor.shutdownNow();
        executor = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
        world = new World();
    }

    public static World getWorld() {
        return world;
    }

    public static Random getRandomGenerator() {
        return rand;
    }
}
