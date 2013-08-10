package za.co.sourlemon.zambies.ems.systems;

import za.co.sourlemon.zambies.ems.ISystem;
import za.co.sourlemon.zambies.EntityFactory;
import za.co.sourlemon.zambies.ems.nodes.ZambieAttractorNode;
import java.util.List;
import za.co.sourlemon.zambies.ems.Engine;

public class ZambieAttractorSystem implements ISystem
{
    Engine engine;
    EntityFactory factory;

    public ZambieAttractorSystem(EntityFactory factory)
    {
        this.factory = factory;
    }

    @Override
    public boolean start(Engine engine)
    {
        this. engine = engine;
        return true;
    }

    @Override
    public void update(double delta)
    {
        List<ZambieAttractorNode> nodes = engine.getNodeList(ZambieAttractorNode.class);
        
        for (ZambieAttractorNode node : nodes)
        {
        	node.attractor.lastSpawn += delta;
        	if (node.attractor.lastSpawn >= node.attractor.spawnInterval)
        	{
        		factory.createZambie(node.position.x,node.position.y,
        			node.attractor.distance,node.attractor.speed);
        		node.attractor.lastSpawn = 0;
        	}
        }
    }

    @Override
    public void end()
    {
    }
}