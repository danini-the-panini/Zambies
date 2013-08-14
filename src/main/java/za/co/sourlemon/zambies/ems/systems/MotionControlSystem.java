package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.KeyEvents;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import za.co.sourlemon.zambies.ems.nodes.MotionControlNode;

/**
 *
 * @author daniel
 */
public class MotionControlSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        KeyEvents keyboard = engine.getNode(EventNode.class).keyboard;
        
        List<MotionControlNode> nodes = engine.getNodeList(MotionControlNode.class);
        
        for (MotionControlNode node : nodes)
        {
            if (keyboard.keys[node.control.up])
            {
                node.position.y -= node.control.speed * delta;
            }
            if (keyboard.keys[node.control.down])
            {
                node.position.y += node.control.speed * delta;
            }
            if (keyboard.keys[node.control.left])
            {
                node.position.x -= node.control.speed * delta;
            }
            if (keyboard.keys[node.control.right])
            {
                node.position.x += node.control.speed * delta;
            }
        }
    }

    @Override
    public void end()
    {
    }
    
    
}
