package za.co.sourlemon.zambies.ems.components;

/**
 *
 * @author daniel
 */
public class Gun
{
    public float speed, damage, lifetime;
    public double fireInterval, timeSinceLastFire;
    
    public int numberOfBullets;
    public float scatter;

    public Gun(float speed, float damage, float lifetime, double fireInterval,
            int numberOfBullets, float scatter)
    {
        this.speed = speed;
        this.damage = damage;
        this.lifetime = lifetime;
        this.fireInterval = fireInterval;
        timeSinceLastFire = fireInterval;
        this.numberOfBullets = numberOfBullets;
        this.scatter = scatter;
    }
    
}
