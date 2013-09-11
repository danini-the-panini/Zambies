
package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import java.awt.event.KeyEvent;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.KeyEvents;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import za.co.sourlemon.zambies.ems.nodes.MouseControlNode;

/**
 *
 * @author daniel
 */
public class NoMouseControlSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        KeyEvents keyboard = engine.getNode(EventNode.class).keyboard;
        
        List<MouseControlNode> nodes = engine.getNodeList(MouseControlNode.class);
        
        for (MouseControlNode node : nodes)
        {
            float fakeX = 0, fakeY = 0;
            if (keyboard.keys[KeyEvent.VK_W])
                fakeY -= 1;
            if (keyboard.keys[KeyEvent.VK_S])
                fakeY += 1;
            if (keyboard.keys[KeyEvent.VK_A])
                fakeX -= 1;
            if (keyboard.keys[KeyEvent.VK_D])
                fakeX += 1;

            if (fakeX != 0 || fakeY != 0)
                node.position.theta = (float)Math.atan2(fakeY, fakeX);
        }
    }

    @Override
    public void end()
    {
    }
    
}
