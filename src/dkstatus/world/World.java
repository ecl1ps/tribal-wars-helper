
package dkstatus.world;

/**
 *
 * @author Johny
 */
public class World {
    private final Resources resources = new Resources();
    private final Population population = new Population();
    private final Player player = new Player();

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
    
}
