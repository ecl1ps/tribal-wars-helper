
package dkstatus.world;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johny
 */
public class Player {
    
    private String name = "";
    private int points = 0;
    private boolean isLoggedIn = false;
    private boolean hasAnnounce = false;
    private boolean hasMessage = false;
    private boolean hasForumMessage = false;
    
    private final List<Village> villages = new LinkedList<>();

    public List<Village> getVillages() {
        return villages;
    }

    public void addVillage(Village village) {
        villages.add(village);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean hasAnnounce() {
        return hasAnnounce;
    }

    public void hasAnnounce(boolean hasAnnounce) {
        this.hasAnnounce = hasAnnounce;
    }

    public boolean hasMessage() {
        return hasMessage;
    }

    public void hasMessage(boolean hasMessage) {
        this.hasMessage = hasMessage;
    }

    public boolean hasForumMessage() {
        return hasForumMessage;
    }

    public void hasForumMessage(boolean hasForumMessage) {
        this.hasForumMessage = hasForumMessage;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
}
