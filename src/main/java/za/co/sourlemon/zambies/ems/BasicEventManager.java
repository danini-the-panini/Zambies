/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.sourlemon.zambies.ems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generic event manager. Developers are required to define Event classes that
 * extend Event in order to fire and intercept different kinds of events.
 * 
 * @author Daniel
 */
public class BasicEventManager implements EventManager
{

    private Map<Class<? extends Event>, List<EventListener>> listeners = new HashMap<>();

    @Override
    public <E extends Event> void addEventListener(Class<E> eventClass, EventListener<E> l)
    {
        List<EventListener> list = listeners.get(eventClass);
        if (list == null)
        {
            list = new ArrayList<>();
            listeners.put(eventClass, list);
        }
        list.add(l);
    }

    @Override
    public <E extends Event> void fireEvent(E e)
    {
        List<EventListener> list = listeners.get(e.getClass());
        if (list != null)
        {
            for (EventListener l : list)
            {
                l.event(e);
            }
        }
    }
}
