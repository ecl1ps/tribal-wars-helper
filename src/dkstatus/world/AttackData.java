
package dkstatus.world;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johny
 */
public class AttackData {
    private final Village attacker;
    private final Village target;
    private final List<Unit> attackingUnits = new ArrayList<>(13);

    public AttackData(Village attacker, Village target) {
        this.attacker = attacker;
        this.target = target;
    }

    public Village getAttacker() {
        return attacker;
    }

    public Village getTarget() {
        return target;
    }

    public List<Unit> getAttackingUnits() {
        return attackingUnits;
    }

    public void addUnit(Unit unit) {
        attackingUnits.add(unit);
    }
    
    
}
