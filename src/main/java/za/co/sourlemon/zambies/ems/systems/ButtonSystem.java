/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.sourlemon.zambies.ems.systems;

import java.awt.event.MouseEvent;
import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Event;
import za.co.sourlemon.zambies.ems.EventManager;
import za.co.sourlemon.zambies.ems.components.hud.Button;
import za.co.sourlemon.zambies.ems.components.input.Mouse;
import za.co.sourlemon.zambies.ems.components.input.MouseTap;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import za.co.sourlemon.zambies.ems.nodes.hud.ButtonNode;

/**
 *
 * @author Daniel
 */
public class ButtonSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        Mouse mouse = engine.getNode(EventNode.class).mouse;
        EventManager eventManager = engine.getEventManager();
        
        List<ButtonNode> nodes = engine.getNodeList(ButtonNode.class);
        
        Button selected = null;
        for (ButtonNode node : nodes)
        {
            if (mouse.x > node.hud.x && mouse.x < node.hud.x + node.hud.w
                    && mouse.y > node.hud.y && mouse.y < node.hud.y + node.hud.h)
            {
                selected = node.button;
            }
        }
        for (ButtonNode node : nodes)
        {
            node.hud.color = selected == node.button ?
                    node.button.selectedColor : node.button.baseColor;
        }
        if (selected != null && eventManager.get(new Event(MouseEvent.BUTTON1, MouseTap.class)))
        {
            eventManager.fire(selected.event);
        }
    }

    @Override
    public void end()
    {
    }
    
}
