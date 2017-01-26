package main.java.org.daedalus.math;

/**
 * Created by Stuart on 24/01/2017.
 */
public class Vector3f {

    private float x;
    private float y;
    private float z;

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

    public Vector3f Multiply(Vector3f _vec){
        x *= _vec.x;
        y *= _vec.y;
        z *= _vec.z;

        return this;
    }



    public Vector3f Divide(Vector3f _vec){
        x /= _vec.x;
        y /= _vec.y;
        z /= _vec.z;

        return this;
    }

    public Vector3f Normalise(){


        return
    }

    public double Magnitude(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public void Set(){

    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getZ(){ return z; }

}
