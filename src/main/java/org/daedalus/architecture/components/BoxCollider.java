package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.math.Polygon;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.MeshUtils;
import org.joml.Vector2f;

/**
 * Created by Stuart on 29/01/2017.
 */
public class BoxCollider extends Collider {
        
    private Vector2f size = new Vector2f();
    private Vector2f centre = new Vector2f();
    
    public BoxCollider(){
        super();
    }
    
    @Override
    protected void UpdateMeshPolygon() {
        meshPolygon = Polygon.CreateSquare(size.x, size.y);
    }
    
    @Override
    public void FixedUpdate(){

    }
    
    @Override
    public void Render(){
        if(RENDER_DEBUG_MESH)
            Debug.RenderMeshOutline(renderModel, transform, meshData.tris.length);
    }
    
    public static Vector2f getMin(BoxCollider _box){
        Vector2f halfSize = _box.size.mul(0.5f);
        return new Vector2f(_box.centre.x - halfSize.x, _box.centre.y - halfSize.y);
    }
    
    public static Vector2f getMax(BoxCollider _box){
        Vector2f halfSize = _box.size.mul(0.5f);
        return new Vector2f(_box.centre.x + halfSize.x, _box.centre.y + halfSize.y);
    }
    
    public void SetSize(float _w, float _h){
        if(_w != size.x || _h != size.y) {
            SpriteRenderer renderer = (SpriteRenderer)GetComponent(SpriteRenderer.class);
            UpdateRenderModel(MeshUtils.SquareMesh(_w, _h, renderer.GetSprite().getPivot()));
        }
        
        size.x = _w;
        size.y = _h;
        UpdateMeshPolygon();
    }
    
    public void SetCentre(float _x, float _y){
        if(_x != centre.x || _y != centre.y)
            UpdateRenderModel(MeshUtils.SquareMesh(_x, _y));
    
        centre.x = _x;
        centre.y = _y;
        UpdateMeshPolygon();
    }
    
}
