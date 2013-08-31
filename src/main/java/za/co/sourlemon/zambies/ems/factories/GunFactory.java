package za.co.sourlemon.zambies.ems.factories;

import za.co.sourlemon.zambies.ems.Entity;
import za.co.sourlemon.zambies.ems.Factory;
import za.co.sourlemon.zambies.ems.components.Gun;
import za.co.sourlemon.zambies.ems.components.Offset;
import za.co.sourlemon.zambies.ems.components.Position;
import za.co.sourlemon.zambies.ems.components.Renderable;
import za.co.sourlemon.zambies.ems.components.Usable;

/**
 *
 * @author daniel
 */
public class GunFactory implements Factory<GunFactoryRequest>
{

    @Override
    public Entity create(GunFactoryRequest request)
    {
        Entity entity = new Entity();
        entity.add(new Usable());
        entity.add(new Gun(request.speed, request.damage, request.lifetime,
                request.fireInterval, request.numberOfBullets, request.scatter));
        entity.add(new Offset(request.offsetX, request.offsetY, 0));
        entity.add(new Position(request.x, request.y, 0, request.scaleX, request.scaleY));
        entity.add(new Renderable(request.color));
        
        return entity;
    }
    
}
