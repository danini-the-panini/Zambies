package za.co.sourlemon.zambies;

import za.co.sourlemon.zambies.ems.systems.MotionSystem;
import za.co.sourlemon.zambies.ems.systems.RenderSystem;
import za.co.sourlemon.zambies.ems.systems.MotionControlSystem;
import za.co.sourlemon.zambies.ems.systems.MouseControlSystem;
import za.co.sourlemon.zambies.ems.systems.ZambieAISystem;
import za.co.sourlemon.zambies.ems.systems.LifeSystem;
import za.co.sourlemon.zambies.ems.systems.ZambieAttractorSystem;
import za.co.sourlemon.zambies.ems.systems.LifetimeSystem;
import za.co.sourlemon.zambies.ems.systems.GunControlSystem;
import za.co.sourlemon.zambies.ems.systems.CameraLockSystem;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import za.co.sourlemon.zambies.ems.Engine;

/**
 *
 * @author user
 */
public class App
{

    public static final double NANOS_PER_SECOND = 1000000000.0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        RenderCanvas canvas = new RenderCanvas();
        final Keyboard keyboard = new Keyboard();
        canvas.addKeyListener(keyboard);
        final Camera camera = new Camera();
        canvas.addComponentListener(camera);
        final Mouse mouse = new Mouse(camera);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        
        final Frame frame = new Frame("Zambies!");
        frame.add(canvas);
        frame.setSize(800, 600);
        frame.setVisible(true);
        canvas.init();
        canvas.requestFocus();
        
        final Engine engine = new Engine();
        final EntityFactory factory = new EntityFactory(engine, keyboard, mouse);
        
        engine.addSystem(new ZambieAttractorSystem(factory));
        engine.addSystem(new ZambieAISystem());
        engine.addSystem(new LifetimeSystem());
        engine.addSystem(new MotionControlSystem(keyboard));
        engine.addSystem(new MotionSystem());
        engine.addSystem(new MouseControlSystem(mouse));
        engine.addSystem(new GunControlSystem(keyboard, mouse, factory));
        engine.addSystem(new LifeSystem());
        engine.addSystem(new CameraLockSystem(camera));
        engine.addSystem(new RenderSystem(canvas,camera,Color.WHITE));
        
        final AtomicBoolean running = new AtomicBoolean(true);
        
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                running.set(false);
                engine.shutDown();
                frame.dispose();
                System.exit(0);
            }
        });
        
        new Thread(new Runnable() {

            long lastTime = System.nanoTime();
            
            @Override
            public void run()
            {
                while (running.get())
                {
                    long now = System.nanoTime();
                    double delta = (double)(now - lastTime)/NANOS_PER_SECOND;
                    lastTime = now;

                    engine.update(delta);
                }
            }
        }).start();
        
        factory.createSurvivor(0, 0);
    }
}
