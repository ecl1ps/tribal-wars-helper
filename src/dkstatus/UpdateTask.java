
package dkstatus;

import dkstatus.requests.IUpdateRequest;
import dkstatus.world.World;
import java.io.EOFException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.HttpHostConnectException;

/**
 *
 * @author Johny
 */
public class UpdateTask extends TimerTask {

    private final World world;
    private final IUpdateRequest request;
    
    public UpdateTask(IUpdateRequest request, World world) {
        this.world = world;
        this.request = request;
    }
    
    @Override
    public void run() {
        try {
            request.updateData(world);
        } catch (EOFException ex) {
            // request error (timeout?)   
            Logger.getLogger(UpdateTask.class.getName()).log(Level.INFO, "Exception: {0} - rescheduling", ex.toString());
            request.reschedule();
        } catch (UnknownHostException | HttpHostConnectException | NoRouteToHostException | HttpResponseException ex) {
            // without internet connection
            Logger.getLogger(UpdateTask.class.getName()).log(Level.INFO, "Exception: {0} - rescheduling", ex.toString());
            world.getPlayer().setName("Offline");
            request.reschedule();
        } catch (Exception ex) { // prevents silent thread termination
            Logger.getLogger(UpdateTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
