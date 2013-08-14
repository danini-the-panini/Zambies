package za.co.sourlemon.zambies.ems;

/**
 * Listener interface for intercepting when a component is added to a listener.
 * Classes should implement this listener if they wish to observe the 
 * components that are added or removed from an entity.
 * 
 * @author daniel
 */
public interface EntityListener
{
    /**
     * Event fired when a component is added to an entity.
     * @param entity the entity the component was added to
     * @param componentClass the class of component added to the entity
     */
    public void componentAdded(Entity entity, Class componentClass);
    
    /**
     * Event fired when a component is removed to an entity.
     * @param entity the entity the component was removed to
     * @param componentClass the class of component removed to the entity
     */
    public void componentRemoved(Entity entity, Class componentClass);
}
