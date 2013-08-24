package za.co.sourlemon.zambies.ems;

/**
 *
 * @author Daniel
 */
public interface Factory<R extends FactoryRequest>
{
    public Entity create(R request);
}
