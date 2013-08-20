package za.co.sourlemon.zambies.ems.systems;

import java.util.List;
import za.co.sourlemon.zambies.ems.AbstractSystem;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.components.Usable;
import za.co.sourlemon.zambies.ems.nodes.PrimaryEquipmentNode;

/**
 *
 * @author daniel
 */
public class PrimaryEquipmentService extends AbstractSystem
{

    @Override
    public void update(double delta)
    {
        List<PrimaryEquipmentNode> nodes = engine.getNodeList(PrimaryEquipmentNode.class);

        for (PrimaryEquipmentNode node : nodes)
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
