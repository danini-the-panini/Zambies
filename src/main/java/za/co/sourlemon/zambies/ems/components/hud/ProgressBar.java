package za.co.sourlemon.zambies.ems.components.hud;

import za.co.sourlemon.zambies.ems.Value;

/**
 *
 * @author Daniel
 */
public class ProgressBar
{
    public float length;
    public Value<? extends Number> max;
    public Value<? extends Number> value;
    public boolean vertical;

    public ProgressBar(Value<? extends Number> max,
            Value<? extends Number> value, float length, boolean vertical)
    {
        this.max = max;
        this.value = value;
        this.length = length;
        this.vertical = vertical;
    }
}
