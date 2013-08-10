package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Keyboard;
import za.co.sourlemon.zambies.Mouse;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.EntityFactory;
import za.co.sourlemon.zambies.ems.ISystem;
import za.co.sourlemon.zambies.ems.nodes.GunControlNode;

/**
 *
 * @author daniel
 */
public class GunControlSystem implements ISystem
{
    Keyboard keyboard;
    Mouse mouse;
    EntityFactory factory;
    Engine engine;

    public GunControlSystem(Keyboard keyboard, Mouse mouse, EntityFactory factory)
    {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.factory = factory;
    }

    @Override
    public boolean start(Engine engine)
    {
        this.engine = engine;
        return true;
    }

    @Override
    public void update(double delta)
    {
        List<GunControlNode> nodes = engine.getNodeList(GunControlNode.class);
        
        for (GunControlNode node : nodes)
        {
            node.gun.timeSinceLastFire += delta;
            if ((node.gunControls.mouse ? mouse.button[node.gunControls.trigger] : keyboard.keys[node.gunControls.trigger])
                    && node.gun.timeSinceLastFire >= node.gun.fireInterval)
            {
                node.gun.timeSinceLastFire = 0;
                factory.createBullet(node.gun.damage, node.gun.lifetime,
                        node.position.x, node.position.y,
                        node.position.theta, node.gun.speed);
            }
        }
    }

    @Override
    public void end()
    {
    }
    
}
