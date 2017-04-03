package main.java.org.daedalus.utils;

import main.java.org.daedalus.math.Polygon;
import org.joml.Vector2f;

/**
 * Separating Axis Theorem
 *
 * Created by Stuart on 25/03/2017.
 */
public class SAT {
    
    public static boolean Overlap(Polygon _a, Polygon _b){
        boolean intersects = true;
        
        int edgeCountA = _a.edges.length;
        int edgeCountB = _b.edges.length;
        Vector2f edge;
        for(int i = 0; i < edgeCountA + edgeCountB; i++){
            if(i < edgeCountA)
                edge = _a.edges[i];
            else
                edge = _b.edges[i - edgeCountA];
            
            Vector2f axis = new Vector2f(-edge.y, edge.x);
            axis.normalize();
            
            float minA = 0, minB = 0, maxA = 0, maxB = 0;
            float[] t = Project(axis, _a);
            minA = t[0];
            maxA = t[1];
            t = Project(axis, _b);
            minB = t[0];
            maxB = t[1];
            
            float intervalDist = IntervalDistance(minA, maxA, minB, maxB);
            if(intervalDist > 0)
                intersects = false;
        }
        
        return intersects;
    }
    
    private static float[] Project(Vector2f _a, Polygon _p){
        float dotProduct = _a.dot(_p.points[0]);
        float min = dotProduct, max = dotProduct;
        
        for(int i = 0; i < _p.points.length; i++){
            dotProduct = _p.points[i].dot(_a);
            if(dotProduct < min) min = dotProduct;
            else if(dotProduct > max) max = dotProduct;
        }
        
        return new float[]{ min, max };
    }
    
    private static float IntervalDistance(float minA, float maxA, float minB, float maxB){
        if(minA < minB)
            return minB - maxA;
        else
            return minA - maxB;
    }
    
}
