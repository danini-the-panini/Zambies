package za.co.sourlemon.zambies.ems.components.equipment;

import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.Event;

/**
 *
 * @author Daniel
 */
public class EquipControl
{
    public Event button;
    public Entity slot;

    public EquipControl(Event button, Entity slot)
    {
        this.button = button;
        this.slot = slot;
    }
    
}
