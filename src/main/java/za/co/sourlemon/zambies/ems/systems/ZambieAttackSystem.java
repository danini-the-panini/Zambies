package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Utils;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.Health;
import za.co.sourlemon.zambies.ems.nodes.zambie.ZambieAttractorNode;
import za.co.sourlemon.zambies.ems.nodes.zambie.ZambieClawNode;

/**
 *
 * @author Daniel
 */
public class ZambieAttackSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<ZambieClawNode> znodes = engine.getNodeList(ZambieClawNode.class);
        List<ZambieAttractorNode> anodes = engine.getNodeList(ZambieAttractorNode.class);

        for (ZambieClawNode znode : znodes)
        {
            znode.claws.timeSinceLastAttack += delta;
            
            for (ZambieAttractorNode anode : anodes)
            {
                if (znode.getEntity() == anode.getEntity())
                {
                    continue;
                }

                float zAvgScale = (znode.position.scaleX + znode.position.scaleY) / 2;
                zAvgScale *= zAvgScale;
                float aAvgScale = (anode.position.scaleX + anode.position.scaleY) / 2;
                aAvgScale *= aAvgScale;
                float distSq = Utils.lengthSq(znode.position.x - anode.position.x,
                        znode.position.y - anode.position.y);

                distSq -= (zAvgScale + aAvgScale);

                if (distSq < 0 && znode.claws.timeSinceLastAttack > znode.claws.attackInterval)
                {
                    znode.claws.timeSinceLastAttack = 0;
                    Health health = anode.getEntity().get(Health.class);
                    if (health != null)
                    {
                        health.hp -= znode.claws.damage;
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
