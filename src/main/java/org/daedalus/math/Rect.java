package main.java.org.daedalus.math;

/**
 * Created by Stuart on 26/01/2017.
 */
public class Rect {
    
    private float x, y;
    private float w, h;
    
    public Rect(){
        this(0f, 0f, 0f, 0f);
    }
    
    public Rect(float _x, float _y, float _w, float _h){
        x = _x;
        y = _y;
        w = _w;
        h = _h;
    }
    
    public void Set(float _x, float _y, float _w, float _h){
        x = _x;
        y = _y;
        w = _w;
        h = _h;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getWidth() {
        return w;
    }
    
    public float getHeight() {
        return h;
    }
}
