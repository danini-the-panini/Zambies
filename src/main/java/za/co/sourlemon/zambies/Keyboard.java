package za.co.sourlemon.zambies;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author daniel
 */
public class Keyboard extends KeyAdapter
{
    
    public boolean[] keys = new boolean[65535];

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }
    
}
