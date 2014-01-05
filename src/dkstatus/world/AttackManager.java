
package dkstatus.world;

import dkstatus.WebRequestService;
import dkstatus.requests.AttackRequest;

/**
 *
 * @author Johny
 */
public class AttackManager {
    
    public static void executeAttack(AttackData data) {
         WebRequestService.scheduleTask(new AttackRequest(data), 0);
    }
}
