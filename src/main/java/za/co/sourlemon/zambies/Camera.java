package za.co.sourlemon.zambies;

import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;

/**
 *
 * @author daniel
 */
public class Camera implements ComponentListener
{
    // screen offset
    private float sox, soy;
    
    // offset
    public float x, y;

    public Camera()
    {
        this(0, 0);
    }
    
    public Camera(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void transform(Graphics2D g)
    {
        g.transform(AffineTransform.getTranslateInstance(x+sox, y+soy));
    }

    @Override
    public void componentHidden(ComponentEvent e)
    {}

    @Override
    public void componentMoved(ComponentEvent e)
    {}

    @Override
    public void componentResized(ComponentEvent e)
    {
        sox = e.getComponent().getWidth()/2;
        soy = e.getComponent().getHeight()/2;
    }

    @Override
    public void componentShown(ComponentEvent e)
    {}

    public float getX()
    {
        return x+sox;
    }

    public float getY()
    {
        return y+soy;
    }
    
}
