
package dkstatus.world;

import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class RecruitmentData {
    private int barracksCount;
    private DateTime barracksFinished;
    private int stableCount;
    private DateTime stableFinished;
    private int garageCount;
    private DateTime garageFinished;    

    public int getBarracksCount() {
        return barracksCount;
    }

    public void setBarracksCount(int barracksCount) {
        this.barracksCount = barracksCount;
    }

    public DateTime getBarracksFinished() {
        return barracksFinished;
    }

    public void setBarracksFinished(DateTime barracksFinished) {
        this.barracksFinished = barracksFinished;
    }

    public int getStableCount() {
        return stableCount;
    }

    public void setStableCount(int stableCount) {
        this.stableCount = stableCount;
    }

    public DateTime getStableFinished() {
        return stableFinished;
    }

    public void setStableFinished(DateTime stableFinished) {
        this.stableFinished = stableFinished;
    }

    public int getGarageCount() {
        return garageCount;
    }

    public void setGarageCount(int garageCount) {
        this.garageCount = garageCount;
    }

    public DateTime getGarageFinished() {
        return garageFinished;
    }

    public void setGarageFinished(DateTime garageFinished) {
        this.garageFinished = garageFinished;
    }
    
    
}
