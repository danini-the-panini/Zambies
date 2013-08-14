package za.co.sourlemon.zambies.ems.systems;

import java.awt.Color;
import za.co.sourlemon.zambies.ems.nodes.RenderNode;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.List;
import za.co.sourlemon.zambies.Camera;
import za.co.sourlemon.zambies.RenderCanvas;
import za.co.sourlemon.zambies.ems.AbstractSystem;

/**
 *
 * @author daniel
 */
public class RenderSystem extends AbstractSystem
{
    Color bg;
    Camera camera;
    RenderCanvas canvas;

    public RenderSystem(RenderCanvas canvas, Camera camera, Color bg)
    {
        this.bg = bg;
        this.camera = camera;
        this.canvas = canvas;
    }

    @Override
    public void update(double delta)
    {
        List<RenderNode> nodes = engine.getNodeList(RenderNode.class);
        
        Graphics2D g = (Graphics2D) canvas.strategy.getDrawGraphics();
        
        g.setBackground(bg);
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        AffineTransform origT = g.getTransform();
        camera.transform(g);
        
        for (RenderNode r : nodes)
        {
            AffineTransform oldT = g.getTransform();
            
            g.transform(AffineTransform.getTranslateInstance(
                r.position.x, r.position.y));
            g.transform(AffineTransform.getRotateInstance(r.position.theta));
            g.transform(AffineTransform.getScaleInstance(r.position.scaleX, r.position.scaleY));
            
            g.setColor(r.renderable.color);
            g.fillRect(-1, -1, 2, 2);
            
            g.setTransform(oldT);
        }
        
        g.setTransform(origT);
        
        g.dispose();
        
        canvas.strategy.show();
        
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void end()
    {
    }
    
}
