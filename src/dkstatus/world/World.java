
package dkstatus.world;

import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class World {
    
    private final Player player = new Player();
    
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
        for (Village v : player.getVillages())
            v.resetActiveAnnounces();
    }
}
