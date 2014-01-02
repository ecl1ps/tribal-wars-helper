
package dkstatus.world;

import dkstatus.Config;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 *
 * @author Johny
 */
public enum ArmyType {
    
    NOBLE("šlechtic", "slecht", 35),
    RAM_CATAPULT("beranidla/katapulty", "berani", 30),
    SWORDSMAN("šermíři", "serm", 22),
    PIKE_AXE("kopiníci/sekerníci/lučištníci", "sekery", 18),
    HEAVY_CAVALLERY("TK", "TK", 11),
    LIGHT_CAVALLERY("LK/LnK/paladin", "LK", 10),
    SCOUT("zvědi", "zvedi", 9),
    INVALID("neznámé", "unk", 1);
    
    private final String name;
    private final String shortName;
    private final int speed; //  minutes per length unit
    
    private ArmyType(String name, String shortName, int speed) {
        this.name = name;
        this.shortName = shortName;
        this.speed = speed;
    }
    
    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
    
    public Period getTimeToTravell(MapPosition from, MapPosition to) {
        double distance = from.distance(to);
        long ms = (long)(((speed * 60 * 1000 * distance) / Config.WORLD_SPEED) / Config.WORLD_MARCH_SPEED);
        return new Period(ms, PeriodType.time());
    }
}
