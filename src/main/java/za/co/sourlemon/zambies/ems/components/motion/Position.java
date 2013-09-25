package za.co.sourlemon.zambies.ems.components.motion;

/**
 *
 * @author daniel
 */
public class Position
{
    public float px, py;
    public float x, y, theta, scaleX, scaleY;

    public Position(float x, float y, float theta, float scaleX, float scaleY)
    {
        this.x = x;
        this.y = y;
        px = x;
        py = y;
        this.theta = theta;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
}
