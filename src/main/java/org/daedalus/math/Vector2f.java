package main.java.org.daedalus.math;

/**
 * Created by Stuart on 24/01/2017.
 */
public class Vector2f {
    
    private float x;
    private float y;
    
    public Vector2f(){
        this(0f, 0f);
    }
    
    public Vector2f(Vector3f _vec){
        this(_vec.getX(), _vec.getY());
    }
    
    public Vector2f(Vector2f _vec){
        this(_vec.x, _vec.y);
    }
    
    public Vector2f(float _x, float _y){
        x = _x;
        y = _y;
    }
    
    public Vector2f Add(Vector2f _vec){
        x += _vec.x;
        y += _vec.y;
        
        return this;
    }
    
    public Vector2f Sub(Vector2f _vec){
        x -= _vec.x;
        y -= _vec.y;
        
        return this;
    }
    
    public Vector2f Multiply(double _scalar){
        x *= _scalar;
        y *= _scalar;
        
        return this;
    }
    
    public Vector2f Divide(double _scalar){
        x /= _scalar;
        y /= _scalar;
        
        return this;
    }
    
    public double Magnitude(){
        return Math.sqrt(x*x + y*y);
    }
    
    public Vector2f Normalise(){
        Divide(Magnitude());
        
        return this;
    }
    
    public static float Dot(Vector2f _a, Vector2f _b){
        return _a.x * _b.x + _a.y * _b.y;
    }
    
    public void Set(float _x, float _y){
        x = _x;
        y = _y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }

    public static Vector2f Zero(){
        return new Vector2f(0f, 0f);
    }
    
    public static Vector2f One(){
        return new Vector2f(1f, 1f);
    }
    
    public static Vector2f Up(){
        return new Vector2f(0f, 1f);
    }
    
    public static Vector2f Right(){
        return new Vector2f(1f, 0f);
    }
    
}
