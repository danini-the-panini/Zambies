package za.co.sourlemon.zambies.ems.components;

import za.co.sourlemon.zambies.ems.Event;

/**
 *
 * @author daniel
 */
public class MotionControl
{
    public Event up, down, left, right;
    public float speed;

    public MotionControl(Event up, Event down, Event left, Event right, float speed)
    {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.speed = speed;
    }
    
}
