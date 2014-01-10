
package dkstatus.world;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class Village implements IValidable {
    
    private int id;
    private int points;
    private String name = "";
    private Player owner = new Player();
    private MapPosition position = new MapPosition();
    private int warningFlags = 0;
    
    private final Resources resources = new Resources();
    private final Population population = new Population();
    private final RecruitmentData recruitment = new RecruitmentData();
    
    private final Set<Unit> units = new TreeSet<>();
    
    private final List<MarchingArmy> incomingArmies = new LinkedList<>();
    private final List<MarchingArmy> outgoingArmies = new LinkedList<>();
    
    private DateTime lastUpdateIn = new DateTime();
    
    public Village() {
    }
    
    public Village(int id, String name, Player owner, MapPosition pos) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.position = pos;
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Resources getResources() {
        return resources;
    }

    public Population getPopulation() {
        return population;
    }    

    public RecruitmentData getRecruitmentData() {
        return recruitment;
    }
    
    public boolean IsAttacked() {
        for (MarchingArmy att : incomingArmies)
            if (att.getCommandType() == CommandType.INCOMING_ATTACK)
                return true;
        
        return false;
    }    

    public List<MarchingArmy> getIncomingArmies() {
        return incomingArmies;
    }  

    public List<MarchingArmy> getOutgoingArmies() {
        return outgoingArmies;
    }

    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public MapPosition getPosition() {
        return position;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(MapPosition position) {
        this.position = position;
    }
    
    public double getDistance(MapPosition p) {
        return position.distance(p);
    }
    
    public double getDistance(Village v) {
        return position.distance(v.getPosition());
    }    

    public DateTime getLastUpdateIn() {
        return lastUpdateIn;
    }

    public void setLastUpdateIn(DateTime lastUpdateIn) {
        this.lastUpdateIn = lastUpdateIn;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", name, position.toString());
    }
    
    public String toStringSimple() {
        return String.format("%s %s", name, position.toStringCoords());
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
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
        final Village other = (Village) obj;
        return this.id == other.id;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void validate() {
    }

    @Override
    public void invalidate() {
        for (MarchingArmy att : incomingArmies)
            att.invalidate();
        
        for (MarchingArmy att : outgoingArmies)
            att.invalidate();        
    }

    public MarchingArmy getCommandId(int id) {
        for (MarchingArmy att : incomingArmies)
            if (att.getCommandId() == id)
                return att;
        
        for (MarchingArmy att : outgoingArmies)
            if (att.getCommandId() == id)
                return att;
                
        return null;
    }

    public void cleanup() {
        for (int i = 0; i < incomingArmies.size(); ++i) {
            MarchingArmy ma = incomingArmies.get(i);
            if (!ma.isValid())        
                incomingArmies.remove(i);        
        }
        
        for (int i = 0; i < outgoingArmies.size(); ++i) {
            MarchingArmy ma = outgoingArmies.get(i);
            if (!ma.isValid())        
                outgoingArmies.remove(i);        
        }
    }

    public boolean hasActiveAnnounce() {
        for (MarchingArmy att : incomingArmies)
            if (att.hasActiveAnnounce())
                return true;
        
        return false;
    }
    
    public void resetActiveAnnounces() {
        for (MarchingArmy att : incomingArmies)
            att.setActiveAnnounce(false);
    }    

    public Set<Unit> getUnits() {
        return units;
    }

    public void addUnit(Unit u) {
        units.add(u);
    }

    public Unit getUnitByType(UnitType type) {
        for (Unit u : units) {
            if (u.getType() == type)
                return u;
        }        
        return null;
    }

    public boolean hasOwnerPlayer() {
        return owner != null && owner.getId() != 0;
    }

    public int getAvailableUnitCount(UnitType type) {
        for (Unit u : units)
            if (u.getType() == type)
                return u.getInVillage();
        
        return 0;
    }

    public void reduceAvailableUnitCount(UnitType type, int count) {
        for (Unit u : units)
            if (u.getType() == type)
                u.setInVillage(u.getInVillage() - count);
    }

    public int getWarningFlags() {
        return warningFlags;
    }

    public void setWarningFlag(int flag) {
        warningFlags |= flag;
    }
    
    public void removeWarningFlag(int flag) {
        warningFlags &= ~flag;
    }    
    
    public boolean hasWarning() {
        return warningFlags > 0;
    }
}
