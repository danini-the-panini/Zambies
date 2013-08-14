package za.co.sourlemon.zambies;

import java.util.Random;

/**
 *
 * @author user
 */
public class Utils {
    
    public static final Random random = new Random(System.currentTimeMillis());
    
    public static float invSqrt(float x) {
        float xhalf = 0.5f*x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i>>1);
        x = Float.intBitsToFloat(i);
        x = x*(1.5f - xhalf*x*x);
        return x;
    }
    
    public static float dot(float x1, float y1, float x2, float y2)
    {
        return x1*x2+y1*y2;
    }
    
    public static float lengthSq(float x, float y)
    {
        return x*x+y*y;
    }
    
    public static float[] normal(float x, float y)
    {
        float lenSq = lengthSq(x, y);
        float invLen = invSqrt(lenSq);
        
        return new float[]{x*invLen, y*invLen, lenSq};
    }
    
}
