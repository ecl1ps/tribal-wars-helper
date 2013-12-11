
package dkstatus.world;

import dkstatus.Config;
import java.util.Random;

/**
 *
 * @author Johny
 */
public class World {
    private final Random rand = new Random(System.currentTimeMillis());
    
    private final Resources resources = new Resources();
    private final Population population = new Population();
    private final Player player = new Player();
    
    private long nextUpdateIn = 0;

    /**
     * @return the resources
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * @return the population
     */
    public Population getPopulation() {
        return population;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    public void ProcessUpdate() {
        nextUpdateIn = Config.UPDATE_MS + rand.nextInt(Config.UPDATE_JITTER);
    }

    public long getNexUpdateTime() {
        return nextUpdateIn;
    }
    
}
