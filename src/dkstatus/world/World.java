
package dkstatus.world;

import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class World {
    
    private final Player player = new Player();
    private volatile boolean commonDataUpdated;
    
    private DateTime nextUpdateIn = new DateTime();

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    public DateTime getNexUpdateTime() {
        return nextUpdateIn;
    }

    public void setNextUpdateIn(DateTime nextUpdateIn) {
        this.nextUpdateIn = nextUpdateIn;
    }

    public void resetAlert() {
        player.setHasMessageAlertDeactivated(false);
        for (Village v : player.getVillages())
            v.resetActiveAnnounces();
    }

    public void setCommonDataUpdated(boolean updated) {
        commonDataUpdated = updated;
    }

    public boolean hasCommonDataUpdated() {
        return commonDataUpdated;
    }
}
