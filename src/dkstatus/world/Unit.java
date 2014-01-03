
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
    private DateTime recruitmentFinishes;

    public Unit(UnitType type) {
        this.type = type;
        inVillage = 0;
        recruiting = 0;
    }

    public int getInVillage() {
        return inVillage;
    }

    public void setInVillage(int inVillage) {
        this.inVillage = inVillage;
    }

    public int getRecruiting() {
        return recruiting;
    }

    public void setRecruiting(int recruiting) {
        this.recruiting = recruiting;
    }

    public DateTime getRecruitmentFinishes() {
        return recruitmentFinishes;
    }
    
    public int getTotal() {
        return recruiting + inVillage;
    }    

    public void setRecruitmentFinishes(DateTime recruitmentFinshed) {
        this.recruitmentFinishes = recruitmentFinshed;
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
        recruitmentFinishes = new DateTime();
    }
    
}
