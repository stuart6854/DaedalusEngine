package main.java.org.daedalus.math;

/**
 * Created by Stuart on 24/01/2017.
 */
public class Vector3f {

    private float x;
    private float y;
    private float z;
    
    public Vector3f(){
        this(0f, 0f, 0f);
    }
        
    public Vector3f(Vector2f _vec){
        this(_vec.getX(), _vec.getY(), 0f);
    }

    public Vector3f(Vector3f _vec){
        this(_vec.x, _vec.y, _vec.z);
    }

    public Vector3f(float _x, float _y, float _z){
        x = _x;
        y = _y;
        z = _z;
    }

    public Vector3f Add(Vector3f _vec){
        x += _vec.x;
        y += _vec.y;
        z += _vec.z;

        return this;
    }

    public Vector3f Sub(Vector3f _vec){
        x -= _vec.x;
        y -= _vec.y;
        z -= _vec.z;

        return this;
    }

    public Vector3f Multiply(double _scalar){
        x *= _scalar;
        y *= _scalar;
        z *= _scalar;

        return this;
    }
    
    public Vector3f Divide(double _scalar){
        x /= _scalar;
        y /= _scalar;
        z /= _scalar;
        
        return this;
    }
    
    public double Magnitude(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector3f Normalise(){
        Divide(Magnitude());

        return this;
    }

    public static float Dot(Vector3f _a, Vector3f _b){
        return _a.x * _b.x + _a.y * _b.y + _a.z + _b.z;
    }
    
    public void Set(float _x, float _y, float _z){
        x = _x;
        y = _y;
        z = _z;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getZ(){ return z; }

}
