package main.java.org.daedalus.graphics.types;

/**
 * Created by 1622565 on 26/01/2017.
 */
public class Color {

    private float r, g, b, a;

    public Color(float _r, float _g, float _b){
        this(_r, _g, _b, 1.0f);
    }

    public Color(float _r, float _g, float _b, float _a){
        r = _r;
        g = _g;
        b = _b;
        a = _a;
    }

    public float getR() {
        return r;
    }

    public void SetR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void SetG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void SetB(float b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }
    
    public static Color White(){
        return new Color(1f, 1f, 1f);
    }
    
    public static Color Grey(){
        return new Color(0.5f, 0.5f, 0.5f);
    }
    
    public static Color Black(){
        return new Color(0f, 0f, 0f);
    }
    
    public static Color Red(){
        return new Color(1f, 0f, 0f);
    }
    
    public static Color Green(){
        return new Color(0f, 1f, 0f);
    }
    
    public static Color Blue(){
        return new Color(0f, 0f, 1f);
    }
    
    public static Color Yellow(){
        return new Color(1f, 1f, 0f);
    }
    
    public static Color Cyan(){
        return new Color(0f, 1f, 1f);
    }
    
    public static Color Magenta(){
        return new Color(1f, 0f, 1f);
    }
    
}
