
package dkstatus.world;

import dkstatus.Config;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 *
 * @author Johny
 */
public enum ArmyType {
    
    NOBLE("šlechtic", 35),
    RAM_CATAPULT("beranidla/katapulty", 30),
    SWORDSMAN("šermíři", 22),
    PIKE_AXE("kopiníci/sekerníci/lučištníci", 18),
    HEAVY_CAVALLERY("těžká kavalérie", 11),
    LIGHT_CAVALLERY("lehká kavalérie/lučištník na koni/paladin", 10),
    SCOUT("zvědi", 9),
    INVALID("neznámé", 1);
    
    private final String name;
    private final int speed; //  minutes per length unit
    
    private ArmyType(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }
    
    public String getName() {
        return name;
    }
    
    public Period getTimeToTravell(MapPosition from, MapPosition to) {
        double distance = from.distance(to);
        long ms = (long)(((speed * 60 * 1000 * distance) / Config.WORLD_SPEED) / Config.WORLD_MARCH_SPEED);
        return new Period(ms, PeriodType.time());
    }
}
