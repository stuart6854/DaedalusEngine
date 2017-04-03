package main.java.org.daedalus.math;

/**
 * Created by Stuart on 02/04/2017.
 */
public class Math {
    
    public static float Clamp(float _val, float _min, float _max){
        if(_val > _max)
            return _max;
        if(_val < _min)
            return _min;
        
        return _val;
    }
    
    public static float Clamp01(float _val){
        return Clamp(_val, 0, 1);
    }
    
}
