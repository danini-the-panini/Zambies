package za.co.sourlemon.zambies.ems.nodes;

import za.co.sourlemon.zambies.ems.Node;
import za.co.sourlemon.zambies.ems.components.KeyEvents;
import za.co.sourlemon.zambies.ems.components.MouseEvents;
import za.co.sourlemon.zambies.ems.components.WindowEvents;

/**
 *
 * @author Daniel
 */
public class EventNode extends Node
{
    public WindowEvents window;
    public KeyEvents keyboard;
    public MouseEvents mouse;
}
