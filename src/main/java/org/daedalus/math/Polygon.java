package main.java.org.daedalus.math;

import org.joml.Vector2f;
import java.util.ArrayList;

/**
 * Created by Stuart on 01/04/2017.
 */
public class Polygon {
    
    public Vector2f[] points;
    public Vector2f[] edges;
    
    private Polygon(Vector2f[] _p){
        points = _p;
        
        BuildEdges();
    }
    
    private void BuildEdges(){
        ArrayList<Vector2f> e = new ArrayList<>();
        Vector2f p1, p2;
        
        for(int i = 0; i < points.length - 1; i++){
            p1 = points[i];
            if(i + 1 >= points.length)
                p2 = points[0];
            else
                p2 = points[i + 1];
            
            e.add(p2.sub(p1, new Vector2f()));
        }
        
        edges = e.toArray(new Vector2f[e.size()]);
    }
    
    public static Polygon Create(Vector2f[] _points){
        return new Polygon(_points);
    }
    
    public static Polygon CreateSquare(float _width, float _height){
        float hW = _width / 2f;
        float hH = _height / 2f;
        
        Vector2f[] points = {
                new Vector2f(-hW,  -hH),
                new Vector2f(hW,  -hH),
                new Vector2f(hW,  hH),
                new Vector2f(-hW,  hH)
        };
        
        return new Polygon(points);
    }
    
    public static Polygon CreateCircle(float _radius, int _segments){
        Vector2f[] points = new Vector2f[_segments];
    
        float segmentAngle = 360f / _segments;
        for(int i = 0; i < _segments; i++){
            float angle = segmentAngle * i;
        
            Vector2f seg = new Vector2f();
            seg.x = _radius * (float)org.joml.Math.cos(java.lang.Math.toRadians(angle));
            seg.y = _radius * (float)org.joml.Math.sin(java.lang.Math.toRadians(angle));
            
            points[i] = seg;
        }
        
        return new Polygon(points);
    }
    
}
