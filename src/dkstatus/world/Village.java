
package dkstatus.world;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johny
 */
public class Village implements IValidable {
    
    private int id;
    private String name = "";
    private Player owner = new Player();
    private MapPosition position = new MapPosition();
    
    private final Resources resources = new Resources();
    private final Population population = new Population();
    
    private final List<MarchingArmy> incomingArmies = new LinkedList<>();
    private final List<MarchingArmy> outgoingArmies = new LinkedList<>();
    
    public Village() {
    }
    
    public Village(int id, String name, Player owner, MapPosition pos) {
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

    @Override
    public String toString() {
        return String.format("%s %s", name, position.toString());
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
    
    
}
