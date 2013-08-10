package za.co.sourlemon.zambies.ems.systems;

import za.co.sourlemon.zambies.ems.nodes.MotionNode;
import java.util.List;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.ISystem;

/**
 *
 * @author daniel
 */
public class MotionSystem implements ISystem
{
    Engine engine;
    
    @Override
    public boolean start(Engine engine)
    {
        this.engine = engine;
        return true;
    }

    @Override
    public void update(double delta)
    {
        List<MotionNode> nodes = engine.getNodeList(MotionNode.class);
        
        for (MotionNode m : nodes)
        {
            m.position.px = m.position.x;
            m.position.py = m.position.y;
            
            m.position.x += m.velocity.vx * delta;
            m.position.y += m.velocity.vy * delta;
            m.position.theta += m.velocity.av * delta;
        }
    }

    @Override
    public void end()
    {
    }
    
}
