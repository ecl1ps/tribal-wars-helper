
package dkstatus.world;

/**
 *
 * @author Johny
 */
public class Population {
    private int current = 0;
    private int max = 0;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return current + "/" + max;
    }
    
    
}
