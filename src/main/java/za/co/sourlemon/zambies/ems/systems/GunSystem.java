package za.co.sourlemon.zambies.ems.systems;

import java.awt.Color;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.List;
import za.co.sourlemon.zambies.Utils;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.components.weapon.Bullet;
import za.co.sourlemon.zambies.ems.components.weapon.Gun;
import za.co.sourlemon.zambies.ems.components.Lifetime;
import za.co.sourlemon.zambies.ems.components.Association;
import za.co.sourlemon.zambies.ems.components.motion.Position;
import za.co.sourlemon.zambies.ems.components.Renderable;
import za.co.sourlemon.zambies.ems.components.motion.Velocity;
import za.co.sourlemon.zambies.ems.nodes.weapon.GunNode;

/**
 *
 * @author daniel
 */
public class GunSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<GunNode> nodes = engine.getNodeList(GunNode.class);

        for (GunNode node : nodes)
        {
            node.gun.timeSinceLastFire += delta;
            if (node.usable.using
                    && node.gun.timeSinceLastFire >= node.gun.fireInterval)
            {
                node.gun.timeSinceLastFire = 0;
                createBullet(node.gun, node.position, node.getEntity());

                // to stop glitch where gun doesn't stop firing
                node.usable.using = false;
            }
        }
    }

    @Override
    public void end()
    {
    }

    private void createBullet(Gun gun, Position pos, Entity parent)
    {
        for (int i = 0; i < gun.numberOfBullets; i++)
        {
            Entity entity = new Entity();

            float speed = gun.speed * (1.0f + (float) Utils.random.nextGaussian() * 0.05f);

            float theta = pos.theta + (float) (Utils.random.nextGaussian() * gun.scatter);

            entity.add(new Bullet(gun.damage));
            entity.add(new Lifetime(gun.lifetime));
            entity.add(new Position(pos.x, pos.y, theta, 5, 1));
            entity.add(new Velocity((float) cos(theta) * speed, (float) sin(theta) * speed, 0));
            entity.add(new Association(parent));
            entity.add(new Renderable(Color.RED));

            engine.addEntity(entity);
        }
    }
}
