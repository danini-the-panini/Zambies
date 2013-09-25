
package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.input.Aim;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import za.co.sourlemon.zambies.ems.nodes.input.MouseControlNode;

/**
 *
 * @author daniel
 */
public class MouseControlSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        Aim mouse = engine.getNode(EventNode.class).aim;
        
        List<MouseControlNode> nodes = engine.getNodeList(MouseControlNode.class);
        
        for (MouseControlNode node : nodes)
        {
            node.position.theta = (float)Math.atan2(
                    mouse.y - node.position.y,
                    mouse.x - node.position.x);
        }
    }

    @Override
    public void end()
    {
    }
    
}
