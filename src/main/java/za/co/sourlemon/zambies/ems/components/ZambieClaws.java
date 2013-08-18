package za.co.sourlemon.zambies.ems.components;

/**
 *
 * @author Daniel
 */
public class ZambieClaws
{
    public float damage;
    public double attackInterval, timeSinceLastAttack;

    public ZambieClaws(float damage, double attackInterval)
    {
        this.damage = damage;
        this.attackInterval = attackInterval;
        this.timeSinceLastAttack = attackInterval;
    }
}
