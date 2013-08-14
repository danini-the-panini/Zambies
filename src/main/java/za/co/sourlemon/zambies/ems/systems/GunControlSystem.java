package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.EntityFactory;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import za.co.sourlemon.zambies.ems.nodes.GunControlNode;

/**
 *
 * @author daniel
 */
public class GunControlSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        EventNode events = engine.getNode(EventNode.class);
        
        List<GunControlNode> nodes = engine.getNodeList(GunControlNode.class);
        
        for (GunControlNode node : nodes)
        {
            node.gun.timeSinceLastFire += delta;
            if ((node.gunControls.mouse
                        ? events.mouse.button[node.gunControls.trigger]
                        : events.keyboard.keys[node.gunControls.trigger])
                    && node.gun.timeSinceLastFire >= node.gun.fireInterval)
            {
                node.gun.timeSinceLastFire = 0;
                EntityFactory.createBullet(node.gun.damage, node.gun.lifetime,
                        node.position.x, node.position.y,
                        node.position.theta, node.gun.speed, engine);
            }
        }
    }

    @Override
    public void end()
    {
    }
    
}
