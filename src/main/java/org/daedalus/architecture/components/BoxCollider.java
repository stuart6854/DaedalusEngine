package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.physics.Physics;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.MeshUtils;
import org.jbox2d.collision.Collision;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.joml.Vector2f;

/**
 * Created by Stuart on 29/01/2017.
 */
public class BoxCollider extends Collider {
        
    private Vector2f size = new Vector2f();
    private Vector2f centre = new Vector2f();
    
    private BodyDef bodyDef;
    private Body body;
    private PolygonShape shape;
    
    public BoxCollider(){
        super();
        
        SetSize(100, 100);
        SetCentre(0, 0);
        bodyDef = new BodyDef();
        body = Physics.world.createBody(bodyDef);
        shape = new PolygonShape();
        shape.setAsBox(size.x / 2f, size.y / 2f);
        body.createFixture(shape, 0f);
    }
    
    @Override
    public void FixedUpdate(){
        bodyDef.setPosition(new Vec2(transform.position.x, transform.position.y));
    }
    
    @Override
    public void Render(){
        if(RENDER_DEBUG_MESH)
            Debug.RenderMeshOutline(renderModel, transform, meshData.tris.length);
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
