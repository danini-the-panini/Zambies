package za.co.sourlemon.zambies.ems.components;

/**
 *
 * @author daniel
 */
public class Gun
{
    public float speed, damage, lifetime;
    public double fireInterval, timeSinceLastFire;

    public Gun(float speed, float damage, float lifetime, double fireInterval)
    {
        this.speed = speed;
        this.damage = damage;
        this.lifetime = lifetime;
        this.fireInterval = fireInterval;
        timeSinceLastFire = fireInterval;
    }
    
}
