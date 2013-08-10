package za.co.sourlemon.zambies;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 *
 * @author daniel
 */
public class RenderCanvas extends Canvas
{
    public BufferedImage buffer = null;
    public BufferStrategy strategy;

    public void init()
    {
        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }
    
    
}
