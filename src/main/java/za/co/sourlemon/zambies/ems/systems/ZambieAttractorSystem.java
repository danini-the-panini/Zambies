package za.co.sourlemon.zambies.ems.systems;

import za.co.sourlemon.zambies.EntityFactory;
import za.co.sourlemon.zambies.ems.nodes.ZambieAttractorNode;
import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;

public class ZambieAttractorSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<ZambieAttractorNode> nodes = engine.getNodeList(ZambieAttractorNode.class);

        for (ZambieAttractorNode node : nodes)
        {
            node.attractor.lastSpawn += delta;
            if (node.attractor.lastSpawn >= node.attractor.spawnInterval)
            {
                EntityFactory.createZambie(node.position.x, node.position.y,
                        node.attractor.distance, node.attractor.speed, engine);
                node.attractor.lastSpawn = 0;
            }
        }
    }

    @Override
    public void end()
    {
    }
}