
package dkstatus.world;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Johny
 */
public class IncomingAttack {
    private final Date attackStarted;
    private final String attackerName;
    private final int attackerPosX;
    private final int attackerPosY;

    public IncomingAttack(String attackerName, int attackerPosX, int attackerPosY) {
        attackStarted = new Date();
        this.attackerName = attackerName;
        this.attackerPosX = attackerPosX;
        this.attackerPosY = attackerPosY;
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
     * @return the attackerPosX
     */
    public int getAttackerPosX() {
        return attackerPosX;
    }

    /**
     * @return the attackerPosY
     */
    public int getAttackerPosY() {
        return attackerPosY;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat().format(attackStarted) + " zaútočil " + attackerName + " (" + attackerPosX + "|" + attackerPosY + ')';
    }
    
    
}
