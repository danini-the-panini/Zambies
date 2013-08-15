package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.nodes.RenderNode;
import static za.co.sourlemon.zambies.App.*;
import static za.co.sourlemon.zambies.Utils.*;
import za.co.sourlemon.zambies.ems.components.CameraLock;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.components.Renderable;
import za.co.sourlemon.zambies.ems.nodes.EventNode;

/**
 *
 * @author Daniel
 */
public class LWJGLRenderSystem extends AbstractSystem
{

    private float camX, camY;

    @Override
    public boolean start(Engine engine)
    {
        super.start(engine);

        try
        {
            Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
            Display.create();

            // 2D game, Z-order defined by order of drawing
            GL11.glDisable(GL11.GL_DEPTH_TEST);

            GL11.glClearColor(1, 1, 1, 1);
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
        events.mouse.x = Mouse.getX() - camX - SCREEN_WIDTH / 2;
        events.mouse.y = SCREEN_HEIGHT / 2 - Mouse.getY() - camY;
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

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(
                -camX - SCREEN_WIDTH / 2,
                -camX + SCREEN_WIDTH / 2,
                -camY + SCREEN_HEIGHT / 2,
                -camY - SCREEN_HEIGHT / 2,
                1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        for (RenderNode node : nodes)
        {
            if (node.entity.has(CameraLock.class))
            {
                camX = -node.position.x;
                camY = -node.position.y;
            }

            Renderable r = node.renderable;
            Position p = node.position;
            float[] c = r.color.getRGBComponents(null);
            drawQuad(p.x, p.y,
                    (float) Math.toDegrees(p.theta),
                    p.scaleX, p.scaleY,
                    c[0], c[1], c[2]);
        }

        Display.update();
    }

    public void drawQuad(float x, float y, float theta, float scaleX,
            float scaleY, float r, float g, float b)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glScalef(scaleX, scaleY, 1);
        GL11.glRotatef(theta, 0, 0, 1);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glColor3f(r, g, b);
        GL11.glVertex2f(-1, -1);
        GL11.glVertex2f(1, -1);
        GL11.glVertex2f(1, 1);
        GL11.glVertex2f(-1, 1);

        GL11.glEnd();
        GL11.glPopMatrix();
    }

    @Override
    public void end()
    {
        Display.destroy();
    }

    @Override
    public String toString()
    {
        return "LWJGL";
    }
}
