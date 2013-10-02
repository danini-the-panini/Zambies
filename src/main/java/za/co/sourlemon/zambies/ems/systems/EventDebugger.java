package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.EventManager;
import za.co.sourlemon.zambies.ems.nodes.debug.EventDebugNode;

/**
 *
 * @author Daniel
 */
public class EventDebugger extends AbstractSystem
{

    @Override
    public boolean start(Engine engine)
    {
        System.out.println("EVENT DEBUGGING TURNED ON");
        return super.start(engine);
    }

    @Override
    public void update(double delta)
    {
        EventManager eventManager = engine.getEventManager();
        
        List<EventDebugNode> nodes = engine.getNodeList(EventDebugNode.class);
        
        for (EventDebugNode node : nodes)
        {
            if (eventManager.get(node.eventDebug.event))
            {
                System.out.println("EVENT FIRED: " + node.eventDebug.message);
            }
        }
    }

    @Override
    public void end()
    {
        System.out.println("EVENT DEBUGGING TURNED OFF");
    }
}
