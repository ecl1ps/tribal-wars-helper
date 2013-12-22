
package dkstatus.world;

import java.awt.Point;

/**
 *
 * @author Johny
 */
public class MapPosition {
    private Point position;
    private String continent;

    public MapPosition() {
        position = new Point();
        continent = "K00";
    }

    public MapPosition(Point pos, String continent) {
        this.position = pos;
        this.continent = continent;
    }

    public MapPosition(Point pos) {
        this.position = pos;
        this.continent = "K00";
    }

    public MapPosition(int x, int y, String continent) {
        this.position = new Point(x, y);
        this.continent = continent;
    }
    
    public MapPosition(int x, int y) {
        this.position = new Point(x, y);
        this.continent = "K00";
    }    

    public Point getPosition() {
        return position;
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

    @Override
    public String toString() {
        return "(" + position.x + "|" + position.y + ") " + continent;
    }
    
    public double distance(MapPosition other) {
        return distance(other.getPosition());
    }
    
    public double distance(Point other) {
        return position.distance(other);
    }
}
