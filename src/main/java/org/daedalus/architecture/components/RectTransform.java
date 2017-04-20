package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.math.Rect;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by Stuart on 11/04/2017.
 */
public class RectTransform extends Transform{
    
    public Rect rect;
    
    public enum Anchor{ TL, TR, BL, BR };
    public Anchor anchor = Anchor.TL;
    
    public RectTransform(){
    
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
    
    private Vector3f CalculateWorldPos(){
        Vector3f worldPos = new Vector3f();
        
        
        
        return worldPos;
    }
    
}
