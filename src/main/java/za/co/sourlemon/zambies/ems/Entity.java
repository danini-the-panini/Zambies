package za.co.sourlemon.zambies.ems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import za.co.sourlemon.zambies.ems.components.Association;

/**
 *
 * @author daniel
 */
public class Entity
{

    Collection<EntityListener> listeners = new ArrayList<>();
    Map<Class, Object> components = new HashMap<>();
    Collection<Entity> dependents = new ArrayList<>();
    Collection<Entity> associates = new ArrayList<>();

    public void addEntityListener(EntityListener l)
    {
        listeners.add(l);
    }

    public void removeEntityListener(EntityListener l)
    {
        listeners.remove(l);
    }

    public void add(Object component)
    {
        Class componentClass = component.getClass();
        components.put(componentClass, component);

        for (EntityListener l : listeners)
        {
            l.componentAdded(this, componentClass);
        }
    }

    public void remove(Class componentClass)
    {
        components.remove(componentClass);

        for (EntityListener l : listeners)
        {
            l.componentRemoved(this, componentClass);
        }
    }

    public void associate(Entity other)
    {
        associates.add(other);
        other.add(new Association(this));
    }

    public void unassociate(Entity other)
    {
        if (!associates.contains(other))
        {
            return;
        }
        other.remove(Association.class);
        associates.remove(other);
    }

    public void unassociateAll()
    {
        for (Entity assoc : associates)
        {
            assoc.remove(Association.class);
        }
        associates.clear();
    }

    public Collection<Entity> getAssociates()
    {
        return associates;
    }

    public Collection<Entity> getDependents()
    {
        return dependents;
    }

    public <C> C get(Class<C> componentClass)
    {
        return (C) components.get(componentClass);
    }

    public boolean has(Class componentClass)
    {
        return components.containsKey(componentClass);
    }
}
