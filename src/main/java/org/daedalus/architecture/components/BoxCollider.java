package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.math.Polygon;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.MeshUtils;
import main.java.org.daedalus.utils.SAT;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by Stuart on 29/01/2017.
 */
public class BoxCollider extends Collider {
        
    private Vector2f size = new Vector2f();
    private Vector2f centre = new Vector2f();
    
    public BoxCollider(){
        super();
        
        SetSize(100, 100);
        SetCentre(0, 0);
    }
    
    @Override
    public void FixedUpdate(){

    }
    
    @Override
    public void Render(){
        if(RENDER_DEBUG_MESH)
            Debug.RenderMeshOutline(renderModel, transform, meshData.tris.length);
    }
    
    public boolean DoesCollide(BoxCollider _other){
        Vector2f[] a = new Vector2f[4];
        for(int i = 0; i < meshData.verts.length; i++){
            Vector3f t = meshData.verts[i];
            a[i] = new Vector2f(t.x, t.y).add(transform.position.x, transform.position.y);
        }
        Polygon polyA = new Polygon(a);
        
        Vector2f[] b = new Vector2f[4];
        for(int i = 0; i < _other.meshData.verts.length; i++){
            Vector3f t = _other.meshData.verts[i];
            b[i] = new Vector2f(t.x, t.y).add(_other.transform.position.x, _other.transform.position.y);
        }
        Polygon polyB = new Polygon(b);
        
        return SAT.Overlap(polyA, polyB);
    }
    
    @Override
    protected boolean vsBoxCollider(BoxCollider _box) {
        Vector2f aMin = getMin(this);
        Vector2f aMax = getMax(this);
        Vector2f bMin = getMin(_box);
        Vector2f bMax = getMax(_box);
        
        if(aMax.x < bMin.x || aMin.x > bMax.x) return false;
        if(aMax.y < bMin.y || aMin.y > bMax.y) return false;
        
        return true;
    }
    
    @Override
    protected boolean vsCircleCollider(CircleCollider _circle) {
        return false;
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
        if(_w != size.x || _h != size.y)
            UpdateRenderModel(MeshUtils.SquareMesh(_w, _h, false));
        
        size.x = _w;
        size.y = _h;
    }
    
    public void SetCentre(float _x, float _y){
        if(_x != centre.x || _y != centre.y)
            UpdateRenderModel(MeshUtils.SquareMesh(_x, _y));
    
        centre.x = _x;
        centre.y = _y;
    }
    
}
