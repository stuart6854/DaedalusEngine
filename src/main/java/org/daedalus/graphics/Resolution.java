package main.java.org.daedalus.graphics;

public class Resolution{
    
    public int width, height;
    public boolean fullscreen;
    
    public Resolution(int _width, int _height, boolean _fullscreen){
        width = _width;
        height = _height;
        fullscreen = _fullscreen;
    }
    
    public float getAspectRatio(){
        return width / height;
    }
    
}
