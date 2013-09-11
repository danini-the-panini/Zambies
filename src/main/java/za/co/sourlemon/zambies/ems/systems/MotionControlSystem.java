package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.EventManager;
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
        EventManager eventManager = engine.getEventManager();

        List<MotionControlNode> nodes = engine.getNodeList(MotionControlNode.class);


        for (MotionControlNode node : nodes)
        {
            if (eventManager.get(node.control.up))
            {
                node.position.y -= node.control.speed * delta;
            }
            if (eventManager.get(node.control.down))
            {
                node.position.y += node.control.speed * delta;
            }
            if (eventManager.get(node.control.left))
            {
                node.position.x -= node.control.speed * delta;
            }
            if (eventManager.get(node.control.right))
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
