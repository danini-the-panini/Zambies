package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Utils;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.Health;
import za.co.sourlemon.zambies.ems.nodes.ZambieAttractorNode;
import za.co.sourlemon.zambies.ems.nodes.ZambieClawNode;

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
            for (ZambieAttractorNode anode : anodes)
            {
                if (znode.entity == anode.entity)
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

                if (distSq < 0)
                {
                    Health health = anode.entity.get(Health.class);
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
