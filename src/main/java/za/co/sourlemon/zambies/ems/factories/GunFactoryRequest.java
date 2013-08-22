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
    public float scaleX, scaleY;
    public Color color;

    public GunFactoryRequest(float speed, float damage, float lifetime,
            double fireInterval, int numberOfBullets, float scatter,
            float x, float y, float scaleX, float scaleY, Color color)
    {
        this.speed = speed;
        this.damage = damage;
        this.lifetime = lifetime;
        this.fireInterval = fireInterval;
        this.numberOfBullets = numberOfBullets;
        this.scatter = scatter;
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.color = color;
    }
    
    
}
