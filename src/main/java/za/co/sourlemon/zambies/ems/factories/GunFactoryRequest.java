package za.co.sourlemon.zambies.ems.factories;

import java.awt.Color;
import za.co.sourlemon.zambies.ems.FactoryRequest;

/**
 *
 * @author daniel
 */
public class GunFactoryRequest implements FactoryRequest
{
    public float speed, damage, lifetime;
    public double fireInterval;
    
    public int numberOfBullets;
    public float scatter;
    
    public float x, y;
    public float offsetX, offsetY;
    public float scaleX, scaleY;
    public Color color;
    

    public GunFactoryRequest(float speed, float damage, float lifetime,
            double fireInterval, int numberOfBullets, float scatter,
            float x, float y, float offsetX, float offsetY,
            float scaleX, float scaleY, Color color)
    {
        this.speed = speed;
        this.damage = damage;
        this.lifetime = lifetime;
        this.fireInterval = fireInterval;
        this.numberOfBullets = numberOfBullets;
        this.scatter = scatter;
        this.x = x;
        this.y = y;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.color = color;
    }

    public GunFactoryRequest(GunFactoryRequest other)
    {
        this.speed = other.speed;
        this.damage = other.damage;
        this.lifetime = other.lifetime;
        this.fireInterval = other.fireInterval;
        this.numberOfBullets = other.numberOfBullets;
        this.scatter = other.scatter;
        this.x = other.x;
        this.y = other.y;
        this.offsetX = other.offsetX;
        this.offsetY = other.offsetY;
        this.scaleX = other.scaleX;
        this.scaleY = other.scaleY;
        this.color = other.color;
    }
    
    public GunFactoryRequest atPosition(float x, float y)
    {
        GunFactoryRequest newRequest = new GunFactoryRequest(this);
        newRequest.x = x;
        newRequest.y = y;
        return newRequest;
    }
    
}
