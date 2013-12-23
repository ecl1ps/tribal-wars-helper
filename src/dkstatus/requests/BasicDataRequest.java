package dkstatus.requests;

import dkstatus.Config;
import dkstatus.WebRequestService;
import dkstatus.cookies.BrowserManager;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.io.IOException;
import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class BasicDataRequest implements IUpdateRequest{
    
    @Override
    public void updateData(World world) throws IOException {
        
        if (!world.getPlayer().isLoggedIn()) {
            BrowserManager.refreshCookies();
            WebRequestService.scheduleTask(new VillageListRequest(), 0);
        } else {
            int delay = 0;
            for (Village v : world.getPlayer().getVillages()) {
                delay += VillageDataRequest.calculateDelay();
                WebRequestService.scheduleTask(new VillageDataRequest(v), delay);
            }

            delay = calculateDelay();
            world.setNextUpdateIn(new DateTime().plusMillis(delay));
            WebRequestService.scheduleTask(new BasicDataRequest(), delay);
        }
    }
    
    public static int calculateDelay() {
        return Config.UPDATE_MS + WebRequestService.getRandomGenerator().nextInt(Config.UPDATE_JITTER);
    }       
}
