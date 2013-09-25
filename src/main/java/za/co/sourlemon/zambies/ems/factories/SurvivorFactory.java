package za.co.sourlemon.zambies.ems.factories;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.Event;
import za.co.sourlemon.zambies.ems.Factory;
import za.co.sourlemon.zambies.ems.components.CameraLock;
import za.co.sourlemon.zambies.ems.components.Control;
import za.co.sourlemon.zambies.ems.components.input.KeyPress;
import za.co.sourlemon.zambies.ems.components.input.KeyTap;
import za.co.sourlemon.zambies.ems.components.equipment.EquipControl;
import za.co.sourlemon.zambies.ems.components.Health;
import za.co.sourlemon.zambies.ems.components.motion.MotionControl;
import za.co.sourlemon.zambies.ems.components.input.MouseLook;
import za.co.sourlemon.zambies.ems.components.input.MousePress;
import za.co.sourlemon.zambies.ems.components.motion.Position;
import za.co.sourlemon.zambies.ems.components.Renderable;
import za.co.sourlemon.zambies.ems.components.equipment.Usable;
import za.co.sourlemon.zambies.ems.components.motion.Velocity;
import za.co.sourlemon.zambies.ems.components.zambie.ZambieAttractor;

/**
 *
 * @author Daniel
 */
public class SurvivorFactory implements Factory<SurvivorFactoryRequest>
{

    @Override
    public Entity create(SurvivorFactoryRequest request)
    {
        Entity entity = new Entity();
        
        entity.add(new Position(request.spawnX, request.spawnY, 0, 6, 6));
        entity.add(new Velocity(0, 0, 0));
        entity.add(new MotionControl(
                new Event(KeyEvent.VK_W, KeyPress.class),
                new Event(KeyEvent.VK_S, KeyPress.class),
                new Event(KeyEvent.VK_A, KeyPress.class),
                new Event(KeyEvent.VK_D, KeyPress.class),
                150));
        entity.add(new MouseLook());
        entity.add(new Renderable(Color.BLUE));
        entity.add(new ZambieAttractor(1, 400, 25));
        entity.add(new CameraLock());
        entity.add(new Health(100));
        
        // create equipment slot
        Entity primarySlot = new Entity();
        primarySlot.add(new Control(new Event(MouseEvent.BUTTON1, MousePress.class)));
        //primarySlot.add(new Control(KeyEvent.VK_SPACE, false));
        primarySlot.add(new Usable());
        entity.getDependents().add(primarySlot);
        
        // FIXME: replace with ButtonTap when implemented
        entity.add(new EquipControl(new Event(KeyEvent.VK_E, KeyTap.class),
                primarySlot));
        
        return entity;
    }
}
