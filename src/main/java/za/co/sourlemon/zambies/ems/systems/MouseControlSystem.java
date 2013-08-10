
package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Mouse;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.ISystem;
import za.co.sourlemon.zambies.ems.nodes.MouseControlNode;

/**
 *
 * @author daniel
 */
public class MouseControlSystem implements ISystem
{
    Engine engine;
    Mouse mouse;

    public MouseControlSystem(Mouse mouse)
    {
        this.mouse = mouse;
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
        List<MouseControlNode> nodes = engine.getNodeList(MouseControlNode.class);
        
        for (MouseControlNode node : nodes)
        {
            node.position.theta = (float)Math.atan2(
                    mouse.getY() - node.position.y,
                    mouse.getX() - node.position.x);
        }
    }

    @Override
    public void end()
    {
    }
    
}
