package za.co.sourlemon.zambies.ems.components;

import java.awt.Color;

/**
 *
 * @author Daniel
 */
public class HUD
{

    public int x, y;
    public int w, h;
    public Color color;

    public HUD(int x, int y, int w, int h, Color color)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }
}
