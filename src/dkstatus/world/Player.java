
package dkstatus.world;

/**
 *
 * @author Johny
 */
public class Player {
    public boolean HasAnnounce = false;
    public boolean HasMessage = false;
    public boolean HasForumMessage = false;
    
    public int IncomingAttacks = 0;
    public int IncomingSupports = 0;
    
    public String VillageName = "";
    public int VillagePosX = 0;
    public int VillagePosY = 0;
    
    public boolean IsAttacked() {
        return IncomingAttacks > 0;
    }    
}
