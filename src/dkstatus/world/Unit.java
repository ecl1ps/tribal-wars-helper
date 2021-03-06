
package dkstatus.world;

import java.util.Objects;
import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class Unit implements Comparable<Unit> {
    private final UnitType type;
    private volatile int inVillage;
    private int recruiting;

    public Unit(UnitType type) {
        this.type = type;
        inVillage = 0;
        recruiting = 0;
    }

    public Unit(UnitType type, int count) {
        this.type = type;
        inVillage = count;
        recruiting = 0;
    }

    public int getInVillage() {
        return inVillage;
    }

    public void setInVillage(int inVillage) {
        if (inVillage < 0)
            this.inVillage = 0;
        else
            this.inVillage = inVillage;
    }

    public int getRecruiting() {
        return recruiting;
    }

    public void setRecruiting(int recruiting) {
        this.recruiting = recruiting;
    }

    public int getTotal() {
        return recruiting + inVillage;
    }    

    public UnitType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unit other = (Unit) obj;
        return this.type == other.type;
    }

    @Override
    public int compareTo(Unit o) {
        return type.getId() - o.type.getId();
    }

    public void reset() {
        recruiting = 0;
    }
    
}
