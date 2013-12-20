
package dkstatus.world;

import dkstatus.Utils;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class IncomingAttack implements IValidable {
    
    private boolean valid;
    
    private final int commandId;
    private final Village defender;
    private final int attackerId;
    private final int attackerVillageId;
    private final DateTime attackStarted;
    private final DateTime attackArrives;
    private final String attackerName;
    private final String attackerVillageName;
    private final Point origin;
    private final String attackerContinent;
    private final ArmyType type;

    public IncomingAttack(int commandId, Village defender, 
            int attackerId, String attackerName, 
            int attackerVillageId, String attackerVillageName,
            Point attackerPos, String attackerContinent, DateTime attackArrives) {
        
        valid = true;
        this.attackStarted = new DateTime();
        this.attackArrives = attackArrives;
        this.attackerName = attackerName;
        this.origin = attackerPos;
        this.commandId = commandId;
        this.attackerId = attackerId;
        this.attackerVillageId = attackerVillageId;
        this.attackerVillageName = attackerVillageName;
        this.attackerContinent = attackerContinent;
        this.defender = defender;
        
        type = Utils.calculateAttackType(origin, defender.getPosition(), attackStarted, attackArrives);
    }

    public int getCommandId() {
        return commandId;
    }

    public int getAttackerId() {
        return attackerId;
    }

    public int getAttackerVillageId() {
        return attackerVillageId;
    }

    public DateTime getAttackStarted() {
        return attackStarted;
    }

    public DateTime getAttackArrives() {
        return attackArrives;
    }

    public String getAttackerName() {
        return attackerName;
    }

    public String getAttackerVillageName() {
        return attackerVillageName;
    }

    public Point getOrigin() {
        return origin;
    }

    public String getAttackerContinent() {
        return attackerContinent;
    }

    public ArmyType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s (%s) útočí na %s s %s v %s", 
                attackStarted.toString("dd-MM-yy HH:mm:ss"), 
                attackerName, attackerVillageName, defender.getName(), type.getName(),
                attackArrives.toString("dd-MM-yy HH:mm:ss"));
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void validate() {
        valid = true;
    }

    @Override
    public void invalidate() {
        valid = false;
    }
    
    
}
