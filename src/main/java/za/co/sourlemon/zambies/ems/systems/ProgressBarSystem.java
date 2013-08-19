package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.nodes.ProgressBarNode;

/**
 *
 * @author Daniel
 */
public class ProgressBarSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<ProgressBarNode> nodes = engine.getNodeList(ProgressBarNode.class);

        for (ProgressBarNode node : nodes)
        {
            int progress = Math.round(node.bar.length * node.bar.done);

            if (node.bar.vertical)
            {
                node.hud.h = progress;
            } else
            {
                node.hud.w = progress;
            }
        }
    }

    @Override
    public void end()
    {
    }
}
