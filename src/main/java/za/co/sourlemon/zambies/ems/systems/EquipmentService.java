package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.equipment.Usable;
import za.co.sourlemon.zambies.ems.nodes.equipment.EquipSlotNode;

/**
 *
 * @author daniel
 */
public class EquipmentService extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<EquipSlotNode> nodes = engine.getNodeList(EquipSlotNode.class);

        for (EquipSlotNode node : nodes)
        {
            Usable equipmentUsable = node.equipment.equipped.get(Usable.class);
            if (equipmentUsable == null)
            {
                // TODO: perhaps allow "passive" equipment?
                throw new RuntimeException("Equipment it not usable!");
            }
            else
            {
                equipmentUsable.using = node.usable.using;
            }
        }
    }

    @Override
    public void end()
    {
    }
}
