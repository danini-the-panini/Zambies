package za.co.sourlemon.zambies.ems.nodes;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;

/**
 *
 * @author Daniel
 */
public class HealthBarSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<HealthBarNode> nodes = engine.getNodeList(HealthBarNode.class);
        
        for (HealthBarNode node : nodes)
        {
            node.bar.done = node.health.hp/node.health.maxHp;
        }
    }

    @Override
    public void end()
    {}
    
}
