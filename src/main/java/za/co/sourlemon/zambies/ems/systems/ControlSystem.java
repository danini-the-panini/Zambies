package za.co.sourlemon.zambies.ems.systems;

import java.awt.Color;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.List;
import za.co.sourlemon.zambies.Utils;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.components.Bullet;
import za.co.sourlemon.zambies.ems.components.Lifetime;
import za.co.sourlemon.zambies.ems.components.Parent;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.components.Renderable;
import za.co.sourlemon.zambies.ems.components.Velocity;
import za.co.sourlemon.zambies.ems.nodes.ControlNode;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import za.co.sourlemon.zambies.ems.nodes.GunNode;

/**
 *
 * @author daniel
 */
public class ControlSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        EventNode events = engine.getNode(EventNode.class);

        List<ControlNode> nodes = engine.getNodeList(ControlNode.class);

        for (ControlNode node : nodes)
        {
            node.usable.using = (node.control.mouse
                    ? events.mouse.button[node.control.trigger]
                    : events.keyboard.keys[node.control.trigger]);
        }
    }

    @Override
    public void end()
    {
    }
}
