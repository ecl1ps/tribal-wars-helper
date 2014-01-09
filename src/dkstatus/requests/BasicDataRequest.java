package dkstatus.requests;

import dkstatus.Utils;
import dkstatus.WebRequestService;
import dkstatus.browser.BrowserManager;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.io.IOException;
import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class BasicDataRequest implements IUpdateRequest {
    
    @Override
    public void updateData(World world) throws IOException {
        
        if (!world.getPlayer().isLoggedIn()) {
            BrowserManager.refreshCookies();
            WebRequestService.scheduleTask(new VillageListRequest(), 0);
        } else {
            int delay = 0;
            world.setCommonDataUpdated(false);
            for (Village v : world.getPlayer().getVillages()) {
                WebRequestService.scheduleTask(new VillageDataRequest(v), delay);
                delay += Utils.randSec(1, 3);
            }

            delay = Utils.randSec(30, 60);
            world.setNextUpdateIn(new DateTime().plusMillis(delay));
            WebRequestService.scheduleTask(new BasicDataRequest(), delay);
        }
    }

    @Override
    public void reschedule() {
        WebRequestService.scheduleTask(new BasicDataRequest(), Utils.randSec(60, 2 * 60));
    }
}
