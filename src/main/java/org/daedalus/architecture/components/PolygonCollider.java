package main.java.org.daedalus.architecture.components;

import org.joml.Vector2f;

/**
 * Created by Stuart on 31/03/2017.
 */
public class PolygonCollider extends Collider{
    
    public Vector2f[] points;
    
    @Override
    protected boolean vsBoxCollider(BoxCollider _box) {
        return false;
    }
    
    @Override
    protected boolean vsCircleCollider(CircleCollider _circle) {
        return false;
    }
    
}
