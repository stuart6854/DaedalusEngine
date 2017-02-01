package main.java.org.daedalus.utils;

import main.java.org.daedalus.graphics.types.MeshData;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stuart on 01/02/2017.
 */
public class MeshUtils {
    
    public static MeshData SquareMesh(float _w, float _h){
        return SquareMesh(_w, _h, true);
    }
    
    public static MeshData SquareMesh(float _w, float _h, boolean _centre){
        MeshData meshData = new MeshData();
        
        float startX = (_centre) ? -(_w / 2f) : 0;
        float startY = (_centre) ? -(_h / 2f) : 0;
        
        meshData.verts = new Vector3f[]{
                new Vector3f(startX, startY, 0),
                new Vector3f(startX + _w, startY, 0),
                new Vector3f(startX + _w, startY + _h, 0),
                new Vector3f(startX, startY + _h, 0)
        };
        
        meshData.tris = new int[]{
                0, 1, 2,
                0, 2, 3
        };
        
        meshData.uvs = new Vector2f[]{
                new Vector2f(0, 0),
                new Vector2f(0, 0),
                new Vector2f(0, 0),
                new Vector2f(0, 0)
        };
        
        return meshData;
    }
    
    public static MeshData CircleMesh(float _radius, int _segments, Vector2f _centre){
        MeshData meshData = new MeshData();
        
        meshData.verts = new Vector3f[_segments + 1];
        meshData.verts[0] = new Vector3f(_centre.x, _centre.y, 0);
        
        List<Integer> tris = new ArrayList<>();
        
        float segmentAngle = 360f / _segments;
        for(int i = 0; i < _segments; i++){
            float angle = segmentAngle * i;
            
            Vector3f seg = new Vector3f();
            seg.x = _radius * (float) Math.cos(Math.toRadians(angle)) + _centre.x;
            seg.y = _radius * (float) Math.sin(Math.toRadians(angle)) + _centre.y;
            
            //Vert
            meshData.verts[i + 1] = seg;
            
            //Tris
            tris.add(i + 1);
            tris.add((i == _segments - 1) ? 1 : i + 2);
            tris.add(0);
        }
    
        meshData.tris = Utils.ToArrayInt(tris);
        
        return meshData;
    }
    
}