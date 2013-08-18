package za.co.sourlemon.zambies;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import za.co.sourlemon.zambies.ems.systems.MotionSystem;
import za.co.sourlemon.zambies.ems.systems.AWTRenderSystem;
import za.co.sourlemon.zambies.ems.systems.MotionControlSystem;
import za.co.sourlemon.zambies.ems.systems.MouseControlSystem;
import za.co.sourlemon.zambies.ems.systems.ZambieAISystem;
import za.co.sourlemon.zambies.ems.systems.HealthSystem;
import za.co.sourlemon.zambies.ems.systems.ZambieAttractorSystem;
import za.co.sourlemon.zambies.ems.systems.LifetimeSystem;
import za.co.sourlemon.zambies.ems.systems.GunControlSystem;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.ISystem;
import za.co.sourlemon.zambies.ems.components.CameraLock;
import za.co.sourlemon.zambies.ems.components.Gun;
import za.co.sourlemon.zambies.ems.components.GunControl;
import za.co.sourlemon.zambies.ems.components.Health;
import za.co.sourlemon.zambies.ems.components.KeyEvents;
import za.co.sourlemon.zambies.ems.components.MotionControl;
import za.co.sourlemon.zambies.ems.components.MouseEvents;
import za.co.sourlemon.zambies.ems.components.MouseLook;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.components.Renderable;
import za.co.sourlemon.zambies.ems.components.Velocity;
import za.co.sourlemon.zambies.ems.components.WindowEvents;
import za.co.sourlemon.zambies.ems.components.ZambieAttractor;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import za.co.sourlemon.zambies.ems.systems.BulletSystem;
import za.co.sourlemon.zambies.ems.systems.LWJGLRenderSystem;
import za.co.sourlemon.zambies.ems.systems.ZambieAttackSystem;

/**
 *
 * @author user
 */
public class App
{

    public static final double NANOS_PER_SECOND = 1000000000.0;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final String WINDOW_TITLE = "Zambies!";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ISystem[] renderSystems = new ISystem[]{
            new AWTRenderSystem(),
            new LWJGLRenderSystem()
        };
        
        final Engine engine = new Engine();

        // create event node
        Entity entity = new Entity();
        entity.add(new WindowEvents());
        entity.add(new KeyEvents());
        entity.add(new MouseEvents());
        engine.addEntity(entity);
        
        // create survivor
        entity = new Entity();
        entity.add(new Position(0, 0, 0, 6, 6));
        entity.add(new Velocity(0, 0, 0));
        entity.add(new MotionControl(KeyEvent.VK_W, KeyEvent.VK_S,
                KeyEvent.VK_A, KeyEvent.VK_D, 150));
        entity.add(new MouseLook());
        entity.add(new GunControl(MouseEvent.BUTTON1, true));
        entity.add(new Gun(1000, 10, 0.5f, 0.1));
        entity.add(new Renderable(Color.BLUE));
        entity.add(new ZambieAttractor(0.5, 300, 25));
        entity.add(new CameraLock());
        entity.add(new Health(100));
        engine.addEntity(entity);
        
        engine.addSystem(new ZambieAttractorSystem());
        engine.addSystem(new ZambieAISystem());
        engine.addSystem(new ZambieAttackSystem());
        engine.addSystem(new LifetimeSystem());
        engine.addSystem(new MotionControlSystem());
        engine.addSystem(new MotionSystem());
        engine.addSystem(new MouseControlSystem());
        engine.addSystem(new GunControlSystem());
        engine.addSystem(new BulletSystem());
        engine.addSystem(new HealthSystem());
        engine.addSystem((ISystem)JOptionPane.showInputDialog(
                null, "Select Rendering System", "Render System",
                JOptionPane.QUESTION_MESSAGE, null, renderSystems,
                renderSystems[0]));

        long lastTime = System.nanoTime();

        EventNode events = engine.getNode(EventNode.class);

        while (!events.window.windowClosing)
        {
            long now = System.nanoTime();
            double delta = (double) (now - lastTime) / NANOS_PER_SECOND;
            lastTime = now;

            engine.update(delta);
        }

        engine.shutDown();

    }
}
