
package dkstatus.world;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johny
 */
public class Village {
    
    private int id;
    private Player owner = new Player();
    private String name = "";
    private String continent = "K01";
    private Point position = new Point();
    
    private final Resources resources = new Resources();
    private final Population population = new Population();
    
    private final List<IncomingAttack> incomingAttacks = new LinkedList<>();
    private final List<IncomingAttack> IncomingSupports = new LinkedList<>();
    
    public Village() {
    }
    
    public Village(int id, String name, Player owner, Point pos) {
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
        return !incomingAttacks.isEmpty();
    }    

    public List<IncomingAttack> getIncomingAttacks() {
        return incomingAttacks;
    }  

    public List<IncomingAttack> getIncomingSupports() {
        return IncomingSupports;
    }

    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
    
    public double getDistance(Point p) {
        return position.distance(p);
    }
    
    public double getDistance(Village v) {
        return position.distance(v.getPosition());
    }    

    @Override
    public String toString() {
        return String.format("%s (%d|%d) %s", name, position.x, position.y, continent);
    }
    
}