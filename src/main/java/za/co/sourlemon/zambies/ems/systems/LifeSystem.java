package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import static za.co.sourlemon.zambies.Utils.*;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.nodes.BulletNode;
import za.co.sourlemon.zambies.ems.nodes.LifeNode;

/**
 *
 * @author daniel
 */
public class LifeSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<LifeNode> nodes = engine.getNodeList(LifeNode.class);
        List<BulletNode> bullets = engine.getNodeList(BulletNode.class);
        
        for (LifeNode node : nodes)
        {
            // position of "stationary" entity (e.g. zambie)
            Position np = node.position;
            float scale = (np.scaleX + np.scaleY);
            for (BulletNode bullet : bullets)
            {
                
                // holder variables for clostest point
                float cx, cy;
                
                // bullet position
                Position bp = bullet.position; // <- WC
                
                // normalized vector along bullet trajectory
                float[] nbp = normal(bp.x-bp.px,bp.y-bp.py); // <- V (normalized)
                float proj = dot(np.x-bp.px,np.y-bp.py,nbp[0],nbp[1]); // <- S
                
                if (proj < 0)
                {
                    cx = bp.px;
                    cy = bp.py;
                }
                else if (proj*proj > nbp[2])
                {
                    cx = bp.x;
                    cy = bp.y;
                }
                else
                {
                    cx = proj*nbp[0]+bp.px;
                    cy = proj*nbp[1]+bp.py;
                }
                
                if (lengthSq(np.x-cx, np.y-cy) < scale*scale)
                {
                    engine.removeEntity(bullet.entity);
                    node.life.amount -= bullet.bullet.damage;
                    if (node.life.amount < 0)
                    {
                        engine.removeEntity(node.entity);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void end()
    {
    }
    
}
