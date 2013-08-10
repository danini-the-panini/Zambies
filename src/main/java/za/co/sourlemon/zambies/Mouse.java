package za.co.sourlemon.zambies;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author daniel
 */
public class Mouse extends MouseAdapter
{
    private float x, y;
    public boolean[] button = new boolean[15];
    
    Camera camera;

    public Mouse(Camera camera)
    {
        this.camera = camera;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        button[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        button[e.getButton()] = false;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
    }
    
    public float getX()
    {
        return x - camera.getX();
    }
    
    public float getY()
    {
        return y - camera.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        mouseMoved(e);
    }
    
}
