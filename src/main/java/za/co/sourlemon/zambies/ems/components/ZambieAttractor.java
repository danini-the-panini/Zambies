package za.co.sourlemon.zambies.ems.components;

public class ZambieAttractor
{

    public double lastSpawn = 0, spawnInterval;
    public float distance, speed;

    public ZambieAttractor(double spawnInterval, float distance, float speed)
    {
        this.spawnInterval = spawnInterval;
        lastSpawn = spawnInterval;
        this.distance = distance;
        this.speed = speed;
    }
}