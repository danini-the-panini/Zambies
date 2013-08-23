package za.co.sourlemon.zambies.ems;

/**
 *
 * @author Daniel
 */
public abstract class Event
{
    private int type;

    public Event(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return type;
    }
    
}
