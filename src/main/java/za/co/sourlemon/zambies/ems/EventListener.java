package za.co.sourlemon.zambies.ems;

/**
 *
 * @author Daniel
 */
public interface EventListener<E extends Event>
{
    public void event(E e);
}
