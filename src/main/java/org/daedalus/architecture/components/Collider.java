package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;

/**
 * Created by Stuart on 29/01/2017.
 */
public abstract class Collider extends Component{
    
    public Collider(){
        super(false);
    }
    
    public boolean Collides(Collider _collider){
        if(_collider instanceof BoxCollider)
            return vsBoxCollider((BoxCollider)_collider);
        if(_collider instanceof CircleCollider)
            return vsCircleCollider((CircleCollider)_collider);
        
        return false;
    }
    
    protected abstract boolean vsBoxCollider(BoxCollider _box);
    protected abstract boolean vsCircleCollider(CircleCollider _circle);
    
}
