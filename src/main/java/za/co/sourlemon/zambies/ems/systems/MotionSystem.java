package za.co.sourlemon.zambies.ems.systems;

import za.co.sourlemon.zambies.ems.nodes.motion.MotionNode;
import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;

/**
 *
 * @author daniel
 */
public class MotionSystem extends AbstractSystem
{

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
