package za.co.sourlemon.zambies.ems;

/**
 *
 * @author daniel
 */
public interface ISystem
{
    public boolean start(Engine engine);
    public void update(double delta);
    public void end();
}
