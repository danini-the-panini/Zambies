package za.co.sourlemon.zambies.ems.components;

import za.co.sourlemon.zambies.ems.Entity;

/**
 *
 * @author Daniel
 */
public class EquipControl
{
    public int button;
    public Entity slot;

    public EquipControl(int button, Entity slot)
    {
        this.button = button;
        this.slot = slot;
    }
    
}
