package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Keyboard;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.ISystem;
import za.co.sourlemon.zambies.ems.nodes.MotionControlNode;

/**
 *
 * @author daniel
 */
public class MotionControlSystem implements ISystem
{
    Keyboard keyboard;
    Engine engine;

    public MotionControlSystem(Keyboard keyboard)
    {
        this.keyboard = keyboard;
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
