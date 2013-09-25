package za.co.sourlemon.zambies.ems.factories;

import za.co.sourlemon.zambies.ems.FactoryRequest;
import za.co.sourlemon.zambies.ems.components.weapon.Gun;

/**
 *
 * @author Daniel
 */
public class SurvivorFactoryRequest implements FactoryRequest
{

    protected float spawnX, spawnY;

    public SurvivorFactoryRequest(float spawnX, float spawnY)
    {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }
}
