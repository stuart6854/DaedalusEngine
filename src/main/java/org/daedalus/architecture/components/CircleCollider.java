package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.math.Polygon;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.MeshUtils;
import org.joml.Vector2f;

/**
 * Created by Stuart on 29/01/2017.
 */
public class CircleCollider extends Collider{
    
    public static int DEBUG_MESH_SEGMENTS = 18;
    
    public float radius;
    public Vector2f centre = new Vector2f();
        
    public CircleCollider(){
        super();
        
        SetCentre(0, 0);
        SetRadius(1);
    }
    
    @Override
    protected void UpdateMeshPolygon() {
        meshPolygon = Polygon.CreateCircle(radius, 20);
    }
    
    @Override
    public void Render(){
        if(RENDER_DEBUG_MESH)
            Debug.RenderMeshOutline(renderModel, transform, meshData.tris.length);
            
    }
        
    public void SetRadius(float _r){
        if(_r != radius)
            UpdateRenderModel(MeshUtils.CircleMesh(_r, DEBUG_MESH_SEGMENTS, centre));
        
        radius = _r;
        UpdateMeshPolygon();
    }
    
    public void SetCentre(float _x, float _y){
        if(_x != centre.x || _y != centre.y)
            UpdateRenderModel(MeshUtils.CircleMesh(radius, DEBUG_MESH_SEGMENTS, new Vector2f(_x, _y)));
        
        centre.x = _x;
        centre.y = _y;
        UpdateMeshPolygon();
    }
    
}
