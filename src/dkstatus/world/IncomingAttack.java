
package dkstatus.world;

import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Johny
 */
public class IncomingAttack {
    private final Date attackStarted;
    private final Date attackArrives;
    private final String attackerName;
    private final Point origin;
    private final ArmyType type;

    public IncomingAttack(String attackerName, int attackerPosX, int attackerPosY, Date attackArrives) {
        attackStarted = new Date();
        this.attackArrives = attackArrives;
        this.attackerName = attackerName;
        origin = new Point(attackerPosX, attackerPosY);
        
        type = calculateAttackType();
    }
    
    /**
     * @return the attackStarted
     */
    public Date getAttackStarted() {
        return attackStarted;
    }

    /**
     * @return the attackerName
     */
    public String getAttackerName() {
        return attackerName;
    }

    /**
     * @return the attacker origin
     */
    public Point getAttackerPos() {
        return origin;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat().format(attackStarted) + " zaútočil " + attackerName + " (" + origin.x + "|" + origin.y + ')';
    }

    private ArmyType calculateAttackType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
