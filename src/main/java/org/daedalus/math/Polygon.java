package main.java.org.daedalus.math;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stuart on 01/04/2017.
 */
public class Polygon {
    
    public Vector2f[] points;
    public Vector2f[] edges;
    
    public Polygon(Vector2f[] _p){
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
    
}
