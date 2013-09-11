package za.co.sourlemon.zambies.ems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class BasicEventManager implements EventManager
{
    private HashMap<Event, List<EventListener>> listeners = new HashMap<>();

    @Override
    public void fireEvent(Event event)
    {
        for (EventListener listener : listeners.get(event))
        {
            listener.eventFired(event);
        }
    }

    @Override
    public void listen(Event event, EventListener callback)
    {
        List<EventListener> list = listeners.get(event);
        if (list == null)
        {
            list = new ArrayList<>();
            listeners.put(event, list);
        }
        list.add(callback);
    }
    
}
