package za.co.sourlemon.zambies.ems.nodes.motion;

import za.co.sourlemon.zambies.ems.Node;
import za.co.sourlemon.zambies.ems.components.motion.MotionControl;
import za.co.sourlemon.zambies.ems.components.motion.Position;
import za.co.sourlemon.zambies.ems.components.motion.Velocity;

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
