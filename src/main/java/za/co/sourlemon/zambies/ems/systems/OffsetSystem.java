package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.components.Offset;
import za.co.sourlemon.zambies.ems.components.motion.Position;
import za.co.sourlemon.zambies.ems.nodes.OffsetNode;

/**
 *
 * @author daniel
 */
public class OffsetSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<OffsetNode> nodes = engine.getNodeList(OffsetNode.class);

        for (OffsetNode node : nodes)
        {

            Entity parent = node.parent.entity;

            Position parentPos = parent.get(Position.class);
            if (parentPos == null)
            {
                continue;
            }

            offset(node.position, parentPos, node.offset);
        }
    }
    
    private void offset(Position c, Position p, Offset o)
    {
        float sin = (float)Math.sin(p.theta);
        float cos = (float)Math.cos(p.theta);
        c.x = p.x + o.x * cos - o.y * sin;
        c.y = p.y + o.x * sin + o.y * cos;
        c.theta = p.theta + o.theta;
    }

    @Override
    public void end()
    {
    }
}
