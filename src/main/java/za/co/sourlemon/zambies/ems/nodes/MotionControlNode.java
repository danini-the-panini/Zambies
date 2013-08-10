package za.co.sourlemon.zambies.ems.nodes;

import za.co.sourlemon.zambies.ems.Node;
import za.co.sourlemon.zambies.ems.components.MotionControl;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.components.Velocity;

/**
 *
 * @author daniel
 */
public class MotionControlNode extends Node
{
    public MotionControl control;
    public Position position;
    public Velocity velocity;
}
