package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.nodes.HealthNode;

/**
 *
 * @author daniel
 */
public class HealthSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<HealthNode> nodes = engine.getNodeList(HealthNode.class);

        for (HealthNode node : nodes)
        {
            if (node.life.hp <= 0)
            {
                node.life.hp = 0;
                engine.removeEntity(node.getEntity());
            }
        }
    }

    @Override
    public void end()
    {
    }
}
