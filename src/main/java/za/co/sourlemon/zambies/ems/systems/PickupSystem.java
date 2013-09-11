package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.Utils;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.EventManager;
import za.co.sourlemon.zambies.ems.components.EquipSlot;
import za.co.sourlemon.zambies.ems.components.Pickup;
import za.co.sourlemon.zambies.ems.components.Usable;
import za.co.sourlemon.zambies.ems.nodes.EquipControlNode;
import za.co.sourlemon.zambies.ems.nodes.PickupNode;

/**
 *
 * @author Daniel
 */
public class PickupSystem extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        EventManager eventManager = engine.getEventManager();
        
        List<PickupNode> pnodes = engine.getNodeList(PickupNode.class);
        List<EquipControlNode> enodes = engine.getNodeList(EquipControlNode.class);
        
        for (EquipControlNode enode : enodes)
        {
            // skip if equip key wasn't pressed
            if (!eventManager.get(enode.control.button)) continue;
            
            float escale = (enode.position.scaleX + enode.position.scaleY);
            escale *= escale;
            
            for (PickupNode pnode : pnodes)
            {
                float pscale = (pnode.position.scaleX + pnode.position.scaleY);
                pscale *= pscale;
                
                float distSq = Utils.lengthSq(pnode.position.x - enode.position.x,
                        pnode.position.y - enode.position.y) - (escale + pscale);
                
                if (distSq < 0)
                {
                    Entity slotEntity = enode.control.slot;
                    EquipSlot slot = slotEntity.get(EquipSlot.class);
                    if (slot != null)
                    {
                        // unassociate
                        enode.getEntity().unassociate(slot.equipped);
                        Usable u = slot.equipped.get(Usable.class);
                        slot.equipped.add(new Pickup());
                        
                        // make sure guns triggers don't get stuck
                        if (u != null) u.using = false;
                    }
                    pnode.getEntity().remove(Pickup.class);
                    enode.getEntity().associate(pnode.getEntity());
                    slot.equipped = pnode.getEntity();
                }
            }
        }
    }

    @Override
    public void end()
    {
    }
    
}
