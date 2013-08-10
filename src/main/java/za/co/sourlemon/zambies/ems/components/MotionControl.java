package za.co.sourlemon.zambies.ems.components;

/**
 *
 * @author daniel
 */
public class MotionControl
{
    public int up, down, left, right;
    public float speed;

    public MotionControl(int up, int down, int left, int right, float speed)
    {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.speed = speed;
    }
    
}
