package za.co.sourlemon.zambies;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import za.co.sourlemon.zambies.ems.components.*;
import za.co.sourlemon.zambies.ems.factories.*;
import za.co.sourlemon.zambies.ems.nodes.EventNode;
import za.co.sourlemon.zambies.ems.nodes.HealthBarSystem;
import za.co.sourlemon.zambies.ems.systems.*;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.ISystem;

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
    public static final GunFactoryRequest SUBMACHINE_GUN = new GunFactoryRequest(1000, 10,
            0.5f, 0.1, 1, (float)Math.PI/64.0f, 2, -8, 6, 2, Color.DARK_GRAY);
    public static final GunFactoryRequest SHOTGUN = new GunFactoryRequest(1000, 10,
            0.5f, 0.7, 7, (float)Math.PI/32f, 2, -8, 6, 2, Color.DARK_GRAY);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ISystem[] renderSystems = new ISystem[]
        {
            new AWTRenderSystem(),
            new LWJGLRenderSystem()
        };

        final Engine engine = new Engine();

        // create event node
        Entity eventEntity = new Entity();
        eventEntity.add(new WindowEvents());
        eventEntity.add(new KeyEvents());
        eventEntity.add(new MouseEvents());
        engine.addEntity(eventEntity);

        // create survivor
        SurvivorFactory survivorFactory = new SurvivorFactory();
        Entity survivor = survivorFactory.create(new SurvivorFactoryRequest(0, 0));
        engine.addEntity(survivor);
        
        // create gun
        GunFactory gunFactory = new GunFactory();
        Entity gunEntity = gunFactory.create(SHOTGUN);
        engine.addEntity(gunEntity);
        
        // create equipment slot
        Entity primarySlot = new Entity();
        primarySlot.add(new Control(MouseEvent.BUTTON1, true));
        primarySlot.add(new Usable());
        survivor.getDependents().add(primarySlot);
        engine.addEntity(primarySlot);
        
        // "equip" gun
        survivor.associate(gunEntity);
        primarySlot.add(new EquipSlot(gunEntity));

        // health bar
        Entity healthBarBG = new Entity();
        healthBarBG.add(new HUD(20, 20, 100, 5, Color.RED));
        engine.addEntity(healthBarBG);

        Entity healthBar = new Entity();
        healthBar.add(new ProgressBar(100.0f, 1.0f, false));
        healthBar.add(new HUD(20, 20, 100, 5, Color.GREEN));
        healthBar.add(survivor.get(Health.class));
        engine.addEntity(healthBar);
        engine.addSystem(new ZambieAttractorSystem());
        engine.addSystem(new ZambieAISystem());
        engine.addSystem(new ZambieAttackSystem());
        engine.addSystem(new LifetimeSystem());
        engine.addSystem(new MotionControlSystem());
        engine.addSystem(new MotionSystem());
        engine.addSystem(new MouseControlSystem());
        engine.addSystem(new OffsetSystem());
        engine.addSystem(new ControlSystem());
        engine.addSystem(new EquipmentService());
        engine.addSystem(new GunSystem());
        engine.addSystem(new BulletSystem());
        engine.addSystem(new HealthSystem());
        engine.addSystem(new HealthBarSystem());
        engine.addSystem(new ProgressBarSystem());
        engine.addSystem((ISystem) JOptionPane.showInputDialog(
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
