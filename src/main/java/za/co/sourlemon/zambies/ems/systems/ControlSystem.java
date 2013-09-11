package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.nodes.ControlNode;
import za.co.sourlemon.zambies.ems.nodes.EventNode;

/**
 *
 * @author daniel
 */
public class ControlSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        EventNode events = engine.getNode(EventNode.class);

        List<ControlNode> nodes = engine.getNodeList(ControlNode.class);

        for (ControlNode node : nodes)
        {
            // FIXME: use new event manager for mouse buttons
            node.usable.using = (events.mouse.button[node.control.trigger]);
        }
    }

    @Override
    public void end()
    {
    }
}
