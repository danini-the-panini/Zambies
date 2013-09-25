package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.nodes.hud.ProgressBarNode;

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
            float percent = node.bar.value.get().floatValue()
                    / node.bar.max.get().floatValue();
            int progress = Math.round(node.bar.length * percent);

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
