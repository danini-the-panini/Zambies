package za.co.sourlemon.zambies.ems.components.hud;

import java.awt.Color;
import za.co.sourlemon.zambies.ems.Event;

/**
 *
 * @author Daniel
 */
public class Button
{
    public Event event;
    public Color baseColor;
    public Color selectedColor;

    public Button(Event event, Color baseColor, Color selectedColor)
    {
        this.event = event;
        this.baseColor = baseColor;
        this.selectedColor = selectedColor;
    }
    
}