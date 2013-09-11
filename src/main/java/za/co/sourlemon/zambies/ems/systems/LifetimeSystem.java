package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.nodes.LifetimeNode;

/**
 *
 * @author daniel
 */
public class LifetimeSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<LifetimeNode> nodes = engine.getNodeList(LifetimeNode.class);
        
        for (LifetimeNode node : nodes)
        {
            node.life.value -= delta;
            if (node.life.value <= 0)
            {
                engine.removeEntity(node.getEntity());
            }
        }
    }

    @Override
    public void end()
    {
    }
    
    
}
