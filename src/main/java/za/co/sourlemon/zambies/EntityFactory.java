package za.co.sourlemon.zambies;

import za.co.sourlemon.zambies.ems.components.ZambieAttractor;
import za.co.sourlemon.zambies.ems.components.MouseLook;
import za.co.sourlemon.zambies.ems.components.Renderable;
import za.co.sourlemon.zambies.ems.components.Life;
import za.co.sourlemon.zambies.ems.components.CameraLock;
import za.co.sourlemon.zambies.ems.components.ZambieAI;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import za.co.sourlemon.zambies.ems.components.Bullet;
import za.co.sourlemon.zambies.ems.components.Gun;
import za.co.sourlemon.zambies.ems.components.GunControl;
import za.co.sourlemon.zambies.ems.components.Lifetime;
import za.co.sourlemon.zambies.ems.components.MotionControl;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.components.Velocity;
import static java.lang.Math.*;
import za.co.sourlemon.zambies.ems.Engine;
import za.co.sourlemon.zambies.ems.Entity;

/**
 * Provides some static factory methods for any system to spawn pre-configured game objects.
 * 
 * @author daniel
 */
public final class EntityFactory
{
    private EntityFactory() {}
    
    /**
     * Create a survivor object at the specified point.
     * @param x
     * @param y
     * @param engine 
     */
    public static void createSurvivor(float x, float y, Engine engine)
    {
        Entity entity = new Entity();
        
        entity.add(new Position(x, y, 0, 6, 6));
        entity.add(new Velocity(0, 0, 0));
        entity.add(new MotionControl(KeyEvent.VK_W, KeyEvent.VK_S,
                KeyEvent.VK_A, KeyEvent.VK_D, 150));
        entity.add(new MouseLook());
        entity.add(new GunControl(MouseEvent.BUTTON1, true));
        entity.add(new Gun(1000, 10, 0.5f, 0.1));
        entity.add(new Renderable(Color.BLUE));
        entity.add(new ZambieAttractor(0.5,300,25));
        entity.add(new CameraLock());
        
        engine.addEntity(entity);
    }
    
    /**
     * Create a bullet/projectile at the specified location with specified parameters.
     * @param damage how much damage the bullet can inflict.
     * @param life how long (in seconds) the bullet will exist for.
     * @param x
     * @param y
     * @param theta
     * @param speed
     * @param engine 
     */
    public static void createBullet(float damage, float life, float x, float y, float theta, float speed, Engine engine)
    {
        Entity entity = new Entity();
        
        theta += (float)(Utils.random.nextGaussian()*PI/64f);
        
        entity.add(new Bullet(damage));
        entity.add(new Lifetime(life));
        entity.add(new Position(x, y, theta, 5, 1));
        entity.add(new Velocity((float)cos(theta)*speed, (float)sin(theta)*speed, 0));
        entity.add(new Renderable(Color.RED));
        
        engine.addEntity(entity);
    }

    /**
     * Create a Zambie that is 'dist' units away from the specified location.
     * This is for spawning Zambies around a survivor, for instance.
     * @param x
     * @param y
     * @param dist how far away from the point the Zambie should be spawned.
     * @param speed
     * @param engine 
     */
    public static void createZambie(float x, float y, float dist, float speed, Engine engine)
    {
        Entity entity = new Entity();

        float theta = (float)(random()*PI*2);

        float cos = (float)cos(theta);
        float sin = (float)sin(theta);

        float scale = (float)(4+Math.random()*2);
        entity.add(new Position(x+cos*dist, y+sin*dist, theta+(float)PI, scale, scale));
        entity.add(new Velocity(0, 0, 0));
        entity.add(new ZambieAI(speed+(float)Utils.random.nextGaussian()*(speed*0.1f)));
        entity.add(new Life(30));
        entity.add(new Lifetime(300)); // 5 minute liftime (to stop too many zambies on screen)
        entity.add(new Renderable(Color.GREEN));

        engine.addEntity(entity);
    }
}
