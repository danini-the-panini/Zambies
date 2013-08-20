package za.co.sourlemon.zambies.ems.components;

/**
 *
 * @author Daniel
 */
public class ProgressBar
{
    public float length;
    public float done;
    public boolean vertical;

    public ProgressBar(float length, float done, boolean vertical)
    {
        this.length = length;
        this.done = done;
        this.vertical = vertical;
    }
}
