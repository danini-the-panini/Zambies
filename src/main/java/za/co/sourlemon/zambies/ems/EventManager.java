package za.co.sourlemon.zambies.ems;

/**
 *
 * @author Daniel
 */
public interface EventManager
{
    public <E extends Event> void addEventListener(Class<E> eventClass, EventListener<E> l);
    
    public <E extends Event> void fireEvent(E event);
}
