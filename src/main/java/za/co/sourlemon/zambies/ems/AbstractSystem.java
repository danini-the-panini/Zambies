package za.co.sourlemon.zambies.ems;

/**
 *
 * @author Daniel
 */
public abstract class AbstractSystem implements ISystem
{
    
    protected Engine engine;

    @Override
    public boolean start(Engine engine)
    {
        this.engine = engine;
        return true;
    }
            
}
