package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.nodes.RenderNode;
import static za.co.sourlemon.zambies.App.*;
import static za.co.sourlemon.zambies.Utils.*;
import za.co.sourlemon.zambies.ems.nodes.EventNode;

/**
 *
 * @author Daniel
 */
public class LWJGLRenderSystem extends AbstractSystem
{

    private float camX, camY, camOX, camOY;

    @Override
    public boolean start(Engine engine)
    {
        super.start(engine);

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
        // TODO: use the event buffer to do this.
        
        EventNode events = engine.getNode(EventNode.class);
        events.mouse.x = Mouse.getX() - camX - camOX;
        events.mouse.x = Mouse.getY() - camY - camOY;
        for (int i = 0; i < Mouse.getButtonCount(); i++)
        {
            events.mouse.button[i] = Mouse.isButtonDown(i);
        }
        events.window.windowClosing = Display.isCloseRequested();
        for (int i = 0; i < Keyboard.getKeyCount(); i++)
        {
            int awtKey = LWJGLtoAWT(i);
            events.keyboard.keys[awtKey] = Keyboard.isKeyDown(i);
        }

        List<RenderNode> nodes = engine.getNodeList(RenderNode.class);

        for (RenderNode node : nodes)
        {
            // TODO: render some stuff!
        }

        Display.update();
    }

    @Override
    public void end()
    {
        Display.destroy();
    }
}
