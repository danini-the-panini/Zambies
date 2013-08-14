package za.co.sourlemon.zambies.ems.systems;

import java.awt.Color;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import za.co.sourlemon.zambies.Camera;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.ISystem;
import za.co.sourlemon.zambies.ems.nodes.RenderNode;

/**
 *
 * @author Daniel
 */
public class LWJGLRenderSystem implements ISystem
{
    // TODO: put these in a config file or something

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    Color bg;
    Camera camera;
    Engine engine;

    public LWJGLRenderSystem(Camera camera, Color bg)
    {
        this.bg = bg;
        this.camera = camera;
    }

    @Override
    public boolean start(Engine engine)
    {
        this.engine = engine;

        try
        {
            Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
            Display.create();
        } catch (LWJGLException ex)
        {
            System.err.println("Error creating display: " + ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public void update(double delta)
    {
        // TODO: if (Display.isCloseRequested()) ...

        List<RenderNode> nodes = engine.getNodeList(RenderNode.class);

        for (RenderNode node : nodes)
        {
            
        }
        
        Display.update();
    }

    @Override
    public void end()
    {
        Display.destroy();
    }
}
