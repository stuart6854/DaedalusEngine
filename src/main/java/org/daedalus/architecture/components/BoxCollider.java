package main.java.org.daedalus.architecture.components;

import org.joml.Vector2f;

/**
 * Created by Stuart on 29/01/2017.
 */
public class BoxCollider extends Collider {
        
    private Vector2f size = new Vector2f(1f, 1f);
    private Vector2f centre = new Vector2f(0f, 0f);
    
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
    
}
