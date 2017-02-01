package main.java.org.daedalus.architecture.components;

import org.joml.Vector2f;

/**
 * Created by Stuart on 29/01/2017.
 */
public class CircleCollider extends Collider{
    
    public float radius = 1.0f;
    public Vector2f centre = new Vector2f(0f, 0f);
    
    public CircleCollider(){
        super();
    }
    
    @Override
    protected boolean vsBoxCollider(BoxCollider _box) {
        return false;
    }
    
    @Override
    protected boolean vsCircleCollider(CircleCollider _circle) {
        // Optimised Method
        float r = this.radius + _circle.radius;
        r *= r;
        
        return (r < Math.pow(centre.x + _circle.centre.x, 2f) + Math.pow(centre.y + _circle.centre.y, 2f));
    }
    
    
}
