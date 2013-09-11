package za.co.sourlemon.zambies.ems;

import java.lang.reflect.Field;

/**
 *
 * @author Daniel
 */
public class Value<T>
{

    private Field field;
    private Object obj;

    public Value(String fieldName, Object obj)
    {
        try
        {
            this.field = obj.getClass().getField(fieldName);
        } catch (ReflectiveOperationException ex)
        {
            throw new RuntimeException(ex);
        }
        this.obj = obj;
    }
    
    public T get()
    {
        try
        {
            return (T)field.get(obj);
        } catch (ReflectiveOperationException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    public void set(T value)
    {
        try
        {
            field.set(obj, value);
        } catch (ReflectiveOperationException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
