package za.co.sourlemon.zambies.ems;

/**
 *
 * @author Daniel
 */
public interface EventManager
{
    public void fireEvent(Event event);
    
    public void listen(Event example, EventListener callback);
}
