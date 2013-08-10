package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.ISystem;
import za.co.sourlemon.zambies.ems.nodes.LifetimeNode;

/**
 *
 * @author daniel
 */
public class LifetimeSystem implements ISystem
{
    Engine engine;

    @Override
    public boolean start(Engine engine)
    {
        this.engine = engine;
        return true;
    }

    @Override
    public void update(double delta)
    {
        List<LifetimeNode> nodes = engine.getNodeList(LifetimeNode.class);
        
        for (LifetimeNode node : nodes)
        {
            node.life.value -= delta;
            if (node.life.value <= 0)
            {
                engine.removeEntity(node.entity);
            }
        }
    }

    @Override
    public void end()
    {
    }
    
    
}
