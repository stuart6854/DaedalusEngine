package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;
import main.java.org.daedalus.physics.Physics;
import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by Stuart on 29/01/2017.
 */
public class Rigidbody extends Component{
    
    public boolean useGravity = true;
    
    private Vector3f velocity;
    private Vector3f acceleration;
    
    private float damping;
    
    protected float inverseMass;
    
    public Rigidbody() {
        super(true);
        
        velocity = new Vector3f();
    }
    public void TickPhysics(float _dt){
        if(useGravity)
            velocity.add(Physics.gravity.mul(_dt, new Vector3f()));
        
        transform.position.add(velocity);
    }
    
    public void setMass(float _mass){
        inverseMass = 1f / _mass;
    }
    
    public void setInverseMass(float _inverseMass){
        inverseMass = _inverseMass;
    }
    
}
