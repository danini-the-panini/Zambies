package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Camera;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.nodes.CameraLockNode;

/**
 *
 * @author daniel
 */
public class CameraLockSystem extends AbstractSystem
{
    Camera camera;

    public CameraLockSystem(Camera camera)
    {
        this.camera = camera;
    }

    @Override
    public void update(double delta)
    {
        List<CameraLockNode> nodes = engine.getNodeList(CameraLockNode.class);
        
        for (CameraLockNode node : nodes)
        {
            camera.x = -node.position.x;
            camera.y = -node.position.y;
        }
    }

    @Override
    public void end()
    {}
    
    
    
}
