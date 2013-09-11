package za.co.sourlemon.zambies.ems;

import java.util.HashMap;

/**
 *
 * @author Daniel
 */
public class EventManager
{
    private HashMap<Event, Boolean> events = new HashMap<>();
    
    public void fire(Event event)
    {
        set(event, true);
    }
    
    public void cancel(Event event)
    {
        set(event, false);
    }
    
    public void set(Event event, boolean value)
    {
        events.put(event, value);
    }
    
    public boolean get(Event event)
    {
        return events.get(event) == Boolean.TRUE;
    }
}
