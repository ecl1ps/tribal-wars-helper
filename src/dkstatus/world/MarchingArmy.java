
package dkstatus.world;

import dkstatus.Utils;
import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class MarchingArmy implements IValidable, Comparable<MarchingArmy> {
    
    private boolean valid;
    
    private final int commandId;
    private final CommandType commandType;
    private final Village to;
    private final Village from;
    private final Player toPlayer;
    private final Player fromPlayer;
    private final DateTime marchStarted;
    private final DateTime armyArrives;
    private final ArmyType armyType;

    public MarchingArmy(int commandId, CommandType commandType, Player fromPlayer, Village from, Player toPlayer, Village to, DateTime armyArrives) {
        
        valid = true;
        this.marchStarted = new DateTime();
        this.armyArrives = armyArrives;
        this.commandId = commandId;
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.to = to;
        this.from = from;
        this.commandType = commandType;
        
        if (commandType.isIncoming())
            armyType = Utils.calculateAttackType(from.getPosition(), to.getPosition(), marchStarted, armyArrives);
        else
            armyType = ArmyType.INVALID;
    }

    public int getCommandId() {
        return commandId;
    }

    public Village getTo() {
        return to;
    }

    public Village getFrom() {
        return from;
    }

    public Player getToPlayer() {
        return toPlayer;
    }

    public Player getFromPlayer() {
        return fromPlayer;
    }

    public DateTime getMarchStarted() {
        return marchStarted;
    }

    public DateTime getArmyArrives() {
        return armyArrives;
    }

    public ArmyType getArmyType() {
        return armyType;
    }

    public CommandType getCommandType() {
        return commandType;
    }
    
    @Override
    public String toString() {
        switch (commandType) {
            case INCOMING_ATTACK:
                return String.format("%s: %s (%s) útočí na %s s: %s v %s", 
                    marchStarted.toString("dd.MM.yyyy HH:mm:ss"), 
                    from.toString(), fromPlayer.getName(), to.getName(), armyType.getName(),
                    armyArrives.toString("dd.MM.yyyy HH:mm:ss"));
            case INCOMING_SUPPORT:
                return String.format("%s: %s (%s) posílá podporu pro %s s: %s v %s", 
                    marchStarted.toString("dd.MM.yyyy HH:mm:ss"), 
                    from.toString(), fromPlayer.getName(), to.getName(), armyType.getName(),
                    armyArrives.toString("dd.MM.yyyy HH:mm:ss")); 
            case OUTGOING_ATTACK:
                if (toPlayer.getName().equalsIgnoreCase("---"))
                    return String.format("%s: Útok na %s", 
                        armyArrives.toString("dd.MM.yyyy HH:mm:ss"), 
                        to.toString());
                else
                    return String.format("%s: Útok na %s (%s)", 
                        armyArrives.toString("dd.MM.yyyy HH:mm:ss"), 
                        to.toString(), toPlayer.getName()); 
            case OUTGOING_SUPPORT:
                return String.format("%s: Podpora pro %s (%s)", 
                    armyArrives.toString("dd.MM.yyyy HH:mm:ss"), 
                    to.toString(), toPlayer.getName());
            case RETRIEVING_SUPPORT:
                return String.format("%s: Stažení podpory od %s (%s)", 
                    armyArrives.toString("dd.MM.yyyy HH:mm:ss"), 
                    to.toString(), toPlayer.getName()); 
            case SENT_BACK_SUPPORT:
                return String.format("%s: Podpora zaslána zpět od %s (%s)", 
                    armyArrives.toString("dd.MM.yyyy HH:mm:ss"), 
                    to.toString(), toPlayer.getName());                 
            case RETURNING_ATTACK:
                return String.format("%s: Návrat z útoku na %s", 
                    armyArrives.toString("dd.MM.yyyy HH:mm:ss"), 
                    to.toString());
            case CANCELLED:
                return String.format("%s: Zrušený pochod na %s", 
                    armyArrives.toString("dd.MM.yyyy HH:mm:ss"), 
                    to.toString()); 
            case UNKNOWN_I:
            case UNKNOWN_O:
                return String.format("%s: Neznámý příkaz", 
                    armyArrives.toString("dd.MM.yyyy HH:mm:ss"));                  
        }
        return "Unk";
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

    @Override
    public int compareTo(MarchingArmy o) {
        return armyArrives.compareTo(o.armyArrives);
    }
    
}