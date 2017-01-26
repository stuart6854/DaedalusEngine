package main.java.org.daedalus.graphics;

/**
 * Created by 1622565 on 26/01/2017.
 */
public class Color {

    public static Color white = new Color(1f, 1f, 1f);
    public static Color grey = new Color(0.5f, 0.5f, 0.5f);
    public static Color black = new Color(0f, 0f, 0f);

    public static Color red = new Color(1f, 0f, 0f);
    public static Color green = new Color(0f, 1f, 0f);
    public static Color blue = new Color(0f, 0f, 1f);

    public static Color yellow = new Color(1f, 1f, 0f);
    public static Color cyan = new Color(0f, 1f, 1f);
    public static Color magenta = new Color(1f, 0f, 1f);

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
}
