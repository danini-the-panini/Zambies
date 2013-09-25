package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import static za.co.sourlemon.zambies.Utils.dot;
import static za.co.sourlemon.zambies.Utils.lengthSq;
import static za.co.sourlemon.zambies.Utils.normal;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.Association;
import za.co.sourlemon.zambies.ems.components.motion.Position;
import za.co.sourlemon.zambies.ems.nodes.weapon.BulletNode;
import za.co.sourlemon.zambies.ems.nodes.HealthNode;

/**
 *
 * @author Daniel
 */
public class BulletSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<HealthNode> nodes = engine.getNodeList(HealthNode.class);
        List<BulletNode> bullets = engine.getNodeList(BulletNode.class);

        for (BulletNode bullet : bullets)
        {

            // bullet position
            Position bp = bullet.position; // <- WC

            // normalized vector along bullet trajectory
            float[] nbp = normal(bp.x - bp.px, bp.y - bp.py); // <- V (normalized)

            hnodeloop:
            for (HealthNode node : nodes)
            {
                // prevent survivors from shooting themselves
                Association parent = bullet.getEntity().get(Association.class);
                while (parent != null)
                {
                    if (parent.entity == node.getEntity())
                    {
                        continue hnodeloop;
                    }
                    parent = parent.entity.get(Association.class);
                }

                // position of "stationary" entity (e.g. zambie)
                Position np = node.position;
                float scale = (np.scaleX + np.scaleY);

                // holder variables for closest point
                float cx, cy;

                // projection of bullet-node vector onto bullet trajectory
                float proj = dot(np.x - bp.px, np.y - bp.py, nbp[0], nbp[1]); // <- S

                if (proj < 0)
                {
                    cx = bp.px;
                    cy = bp.py;
                } else if (proj * proj > nbp[2])
                {
                    cx = bp.x;
                    cy = bp.y;
                } else
                {
                    cx = proj * nbp[0] + bp.px;
                    cy = proj * nbp[1] + bp.py;
                }

                if (lengthSq(np.x - cx, np.y - cy) < scale * scale)
                {
                    engine.removeEntity(bullet.getEntity());
                    node.life.hp -= bullet.bullet.damage;
                    break;
                }
            }
        }
    }

    @Override
    public void end()
    {
    }
}
