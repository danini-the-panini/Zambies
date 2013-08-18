package za.co.sourlemon.zambies.ems.systems;

import java.awt.Color;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;
import za.co.sourlemon.zambies.ems.nodes.ZambieAttractorNode;
import java.util.List;
import za.co.sourlemon.zambies.Utils;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.components.Health;
import za.co.sourlemon.zambies.ems.components.Lifetime;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.components.Renderable;
import za.co.sourlemon.zambies.ems.components.Velocity;
import za.co.sourlemon.zambies.ems.components.ZambieAI;
import za.co.sourlemon.zambies.ems.components.ZambieClaws;

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
                createZambie(node.position.x, node.position.y,
                        node.attractor.distance, node.attractor.speed);
                node.attractor.lastSpawn = 0;
            }
        }
    }
    
    private void createZambie(float x, float y, float dist, float speed)
    {
        Entity entity = new Entity();

        float theta = (float)(random()*PI*2);

        float cos = (float)cos(theta);
        float sin = (float)sin(theta);

        float scale = (float)(4+Math.random()*2);
        entity.add(new Position(x+cos*dist, y+sin*dist, theta+(float)PI, scale, scale));
        entity.add(new Velocity(0, 0, 0));
        entity.add(new ZambieAI(speed+(float)Utils.random.nextGaussian()*(speed*0.1f)));
        entity.add(new Health(30));
        entity.add(new Lifetime(300)); // 5 minute liftime (to stop too many zambies on screen)
        entity.add(new ZambieClaws(10));
        entity.add(new Renderable(Color.GREEN));

        engine.addEntity(entity);
    }

    @Override
    public void end()
    {
    }
}