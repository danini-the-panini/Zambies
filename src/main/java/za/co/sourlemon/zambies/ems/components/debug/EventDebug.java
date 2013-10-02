package za.co.sourlemon.zambies.ems.components.debug;

import za.co.sourlemon.zambies.ems.Event;

/**
 *
 * @author Daniel
 */
public class EventDebug
{
    public Event event;
    public String message;

    public EventDebug(Event event, String message)
    {
        this.event = event;
        this.message = message;
    }
    
}
