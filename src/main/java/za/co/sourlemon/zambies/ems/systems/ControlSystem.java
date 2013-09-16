package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.EventManager;
import za.co.sourlemon.zambies.ems.nodes.ControlNode;

/**
 *
 * @author daniel
 */
public class ControlSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        EventManager eventManager = engine.getEventManager();

        List<ControlNode> nodes = engine.getNodeList(ControlNode.class);

        for (ControlNode node : nodes)
        {
            node.usable.using = eventManager.get(node.control.trigger);
        }
    }

    @Override
    public void end()
    {
    }
}
