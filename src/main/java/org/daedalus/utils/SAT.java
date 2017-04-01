package main.java.org.daedalus.utils;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

/**
 * Created by Stuart on 25/03/2017.
 */
public class SAT {

    public static boolean SATTest(Vector2f[] _vertsA, Vector2f[] _vertsB){
        return false;
    }
    
    public static float[] project(Vector2f[] _vertices, Vector2f _axis){
        _axis = _axis.normalize();
        float min = dot(_vertices[0], _axis);
        float max = min;
        for (int i = 0; i < _vertices.length; i++){
            float proj = dot(_vertices[i], _axis);
            if(proj < min) min = proj;
            if(proj > max) max = proj;
        }
        
        float[] arr = {
                min, max
        };
        
        return arr;
    }

    public static float dot(Vector2f _a, Vector2f _b){
        return _a.x * _b.x + _a.y * _b.y;
    }
    
}
