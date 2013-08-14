package za.co.sourlemon.zambies;

import za.co.sourlemon.zambies.ems.systems.MotionSystem;
import za.co.sourlemon.zambies.ems.systems.AWTRenderSystem;
import za.co.sourlemon.zambies.ems.systems.MotionControlSystem;
import za.co.sourlemon.zambies.ems.systems.MouseControlSystem;
import za.co.sourlemon.zambies.ems.systems.ZambieAISystem;
import za.co.sourlemon.zambies.ems.systems.LifeSystem;
import za.co.sourlemon.zambies.ems.systems.ZambieAttractorSystem;
import za.co.sourlemon.zambies.ems.systems.LifetimeSystem;
import za.co.sourlemon.zambies.ems.systems.GunControlSystem;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.components.KeyEvents;
import za.co.sourlemon.zambies.ems.components.MouseEvents;
import za.co.sourlemon.zambies.ems.components.WindowEvents;
import za.co.sourlemon.zambies.ems.nodes.EventNode;

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
        final Engine engine = new Engine();

        Entity entity = new Entity();
        entity.add(new WindowEvents());
        entity.add(new KeyEvents());
        entity.add(new MouseEvents());
        engine.addEntity(entity);

        engine.addSystem(new ZambieAttractorSystem());
        engine.addSystem(new ZambieAISystem());
        engine.addSystem(new LifetimeSystem());
        engine.addSystem(new MotionControlSystem());
        engine.addSystem(new MotionSystem());
        engine.addSystem(new MouseControlSystem());
        engine.addSystem(new GunControlSystem());
        engine.addSystem(new LifeSystem());
        engine.addSystem(new AWTRenderSystem());

        new Thread(new Runnable()
        {
            long lastTime = System.nanoTime();

            @Override
            public void run()
            {
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
        }).start();

        EntityFactory.createSurvivor(0, 0, engine);
    }
}
