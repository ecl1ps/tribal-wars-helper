
package dkstatus.world;

import dkstatus.Config;
import java.awt.Point;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 *
 * @author Johny
 */
public enum ArmyType {
    
    NOBLE("Šlechtic", 27),
    RAM_CATAPULT("Beranidla a katapulty", 23),
    SWORDSMAN("Šermíři", 17),
    PIKE_AXE("Kopiníci a sekerníci", 14),
    HEAVY_CAVALLERY("Těžká kavalérie", 9),
    LIGHT_CAVALLERY("Lehká kavalérie", 8),
    SCOUT("Zvědi", 7),
    INVALID("Neznámé", 1);
    
    private final String name;
    private final int speed; //  minutes per length unit
    
    private ArmyType(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }
    
    public String getName() {
        return name;
    }
    
    public Period getTimeToTravell(Point from, Point to) {
        double distance = from.distance(to);
        double ms = (speed * 60 * 1000 * distance) / Config.WORLD_MARCH_SPEED;
        return new Period(ms, PeriodType.time());
    }
}
