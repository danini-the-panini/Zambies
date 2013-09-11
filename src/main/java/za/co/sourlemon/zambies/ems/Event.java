package za.co.sourlemon.zambies.ems;

import java.util.Objects;

/**
 *
 * @author Daniel
 */
public class Event
{
    private final String symbol;
    private final Object source;

    public Event(String symbol, Object source)
    {
        this.symbol = symbol;
        this.source = source;
    }

    public Object getSource()
    {
        return source;
    }

    public String getSymbol()
    {
        return symbol;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Event)
        {
            Event other = (Event) obj;
            return other.source == source
                    && (symbol == null ? other.symbol == null : symbol.equals(other.symbol));
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.symbol);
        hash = 97 * hash + Objects.hashCode(this.source);
        return hash;
    }
    
}
