package za.co.sourlemon.zambies.ems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The core Engine of the Entity Component System. This class store and keeps
 * track of all entities within the system, as well as providing the main loop
 * for executing each sub-system.
 *
 * @author daniel
 */
public class Engine implements EntityListener
{

    private Collection<ISystem> systems = new ArrayList<>();
    private Collection<Entity> entities = new ArrayList<>();
    private Collection<Entity> toAdd = new ArrayList<>();
    private Collection<Entity> toRemove = new ArrayList<>();
    private Map<Class, Collection<Object>> nodeLists = new HashMap<>();
    private Map<Class, Family> families = new HashMap<>();
    boolean updating = false;

    /**
     * Adds an entity to the list of known entities. Automagically creates and
     * relevant node(s) which is/are added to the appropriate node list. This
     * method may be called at any time as entities will only be physically
     * added to the list once any update loop is completed. This allows systems
     * to add entities during their execution without worrying about concurrent
     * modification exceptions.
     *
     * @param entity the entity to add to the system
     */
    public void addEntity(Entity entity)
    {
        if (updating)
        {
            toAdd.add(entity);
            return;
        }
        entities.add(entity);
        entity.addEntityListener(this);
        for (Family family : families.values())
        {
            family.newEntity(entity);
        }
        for (Entity dep : entity.getDependents())
        {
            addEntity(dep);
        }
    }

    /**
     * Removes an entity from the list of known entities. Automagically removes
     * any nodes associated with this entity from the appropriate node list(s).
     * Similarly to addEntity, this method may be called safely at any time.
     *
     * @param entity the entity to remove from the system
     */
    public void removeEntity(Entity entity)
    {
        if (updating)
        {
            toRemove.add(entity);
            return;
        }
        entity.removeEntityListener(this);

        for (Family family : families.values())
        {
            family.removeEntity(entity);
        }

        entities.remove(entity);

        for (Entity dep : entity.getDependents())
        {
            removeEntity(dep);
        }
        entity.unassociateAll();
    }

    @Override
    public void componentAdded(Entity entity, Class componentClass)
    {
        for (Family family : families.values())
        {
            family.componentAddedToEntity(entity, componentClass);
        }
    }

    @Override
    public void componentRemoved(Entity entity, Class componentClass)
    {
        for (Family family : families.values())
        {
            family.componentRemovedFromEntity(entity, componentClass);
        }
    }

    /**
     * Gets the list of all instances of the specified Node class
     *
     * @param <N> the Node Class type
     * @param nodeClass the Node Class
     * @return the List of all instances of the specified Node class
     */
    public <N extends Node> List<N> getNodeList(Class<N> nodeClass)
    {
        Family family = families.get(nodeClass);
        if (family == null)
        {
            family = new Family(nodeClass);
            for (Entity entity : entities)
            {
                family.newEntity(entity);
            }
            families.put(nodeClass, family);
        }
        return family.getNodeList();
    }

    /**
     * Gets the first instance of the specified node class. This is a
     * convenience method for getting instances of nodes of which only one is
     * expected to exist. (Sort of like the interface to a kind of "singleton"
     * node.
     *
     * @param <N> the Node Class type
     * @param nodeClass the node class
     * @return a single instance of the specified node class
     */
    public <N extends Node> N getNode(Class<N> nodeClass)
    {
        List<N> nodes = getNodeList(nodeClass);
        if (nodes.isEmpty())
        {
            return null;
        }
        return nodes.get(0);
    }

    /**
     * Registers a system to the engine, causing it to be run in the engine's
     * main loop. If an engine's start method fails, the system is not
     * registered to the engine.
     *
     * @param system the system to add
     */
    public void addSystem(ISystem system)
    {
        if (system.start(this))
        {
            systems.add(system);
        }
    }

    /**
     * The main update loop of the engine. This should be called within a
     * constant loop of some kind, where timekeeping must be done. Delta is
     * meant as a convenience parameter, and its meaning is completely up to the
     * developer.
     *
     * @param delta the amount of time since the last update.
     */
    public void update(double delta)
    {
        updating = true;
        for (ISystem s : systems)
        {
            s.update(delta);
        }
        updating = false;

        for (Entity e : toAdd)
        {
            addEntity(e);
        }
        toAdd.clear();
        for (Entity e : toRemove)
        {
            removeEntity(e);
        }
        toRemove.clear();
    }

    /**
     * De-registers a system from the engine. This will call the system's end()
     * method.
     *
     * @param system the system to de-register from the engine
     */
    public void removeSystem(ISystem system)
    {
        if (systems.remove(system))
        {
            system.end();
        }
    }

    /**
     * Shuts down this engine, ending its loop and then calling then end()
     * method of all of the systems.
     */
    public void shutDown()
    {
        while (updating)
        { /* spin */ }

        for (ISystem s : systems)
        {
            s.end();
        }

        systems.clear();
    }
}
