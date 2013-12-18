
package dkstatus.requests;

import dkstatus.world.World;
import java.io.IOException;

/**
 *
 * @author Johny
 */
public interface IUpdateRequest {
    
    /**
     *
     * @param world
     * @throws IOException
     */
    public void updateData(World world) throws IOException;
}
