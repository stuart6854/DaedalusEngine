package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by Stuart on 24/01/2017.
 */
public class Transform extends Component {

    public Vector3f position;
    public Vector3f rotation;
    public Vector2f scale;
    
    public Transform(){
        super(true);
        position = new Vector3f();
        rotation = new Vector3f();
        scale = new Vector2f(1, 1);
    }
    
    public Matrix4f getTransformMatrix(){
        Matrix4f transform = new Matrix4f();
        transform.translate(position);
        transform.rotateX((float)Math.toRadians(rotation.x));
        transform.rotateY((float)Math.toRadians(rotation.y));
        transform.rotateZ((float)Math.toRadians(rotation.z));
        transform.scale(scale.x, scale.y, 1);
        
        return transform;
    }
    
}
