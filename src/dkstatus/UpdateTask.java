
package dkstatus;

import java.util.TimerTask;

/**
 *
 * @author Johny
 */
public class UpdateTask extends TimerTask {
    
    @Override
    public void run() {
        DKStatus.UpdateData();
    }
}
