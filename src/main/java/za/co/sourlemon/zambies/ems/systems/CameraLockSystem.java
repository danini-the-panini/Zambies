package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Camera;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.ISystem;
import za.co.sourlemon.zambies.ems.nodes.CameraLockNode;

/**
 *
 * @author daniel
 */
public class CameraLockSystem implements ISystem
{
    Camera camera;
    Engine engine;

    public CameraLockSystem(Camera camera)
    {
        this.camera = camera;
    }

    @Override
    public boolean start(Engine engine)
    {
        this.engine = engine;
        return true;
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
