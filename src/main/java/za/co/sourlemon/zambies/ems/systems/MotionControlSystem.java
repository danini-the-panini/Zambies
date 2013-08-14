package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Keyboard;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.nodes.MotionControlNode;

/**
 *
 * @author daniel
 */
public class MotionControlSystem extends AbstractSystem
{
    Keyboard keyboard;

    public MotionControlSystem(Keyboard keyboard)
    {
        this.keyboard = keyboard;
    }

    @Override
    public void update(double delta)
    {
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
