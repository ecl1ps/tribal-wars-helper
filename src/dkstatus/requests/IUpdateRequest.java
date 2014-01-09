
package dkstatus.requests;

import dkstatus.world.World;
import java.io.IOException;

/**
 *
 * @author Johny
 */
public interface IUpdateRequest {
    
    /**
     * fetches data from server, processes it and issues update on window
     * @param world
     * @throws IOException
     */
    public void updateData(World world) throws IOException;
    
    /**
     * schedules new task for execution
     */
    public void reschedule();
}
