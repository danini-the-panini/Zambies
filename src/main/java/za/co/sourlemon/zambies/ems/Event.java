package za.co.sourlemon.zambies.ems;

import java.util.Objects;

/**
 *
 * @author Daniel
 */
public class Event
{
    private final int symbol;
    private final Object source;

    public Event(int symbol, Object source)
    {
        this.symbol = symbol;
        this.source = source;
    }

    public Object getSource()
    {
        return source;
    }

    public int getSymbol()
    {
        return symbol;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Event)
        {
            Event other = (Event) obj;
            return other.source.equals(source) && other.symbol == symbol;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + this.symbol;
        hash = 79 * hash + Objects.hashCode(this.source);
        return hash;
    }

    @Override
    public String toString()
    {
        return source.toString() + "::" + symbol;
    }
}
