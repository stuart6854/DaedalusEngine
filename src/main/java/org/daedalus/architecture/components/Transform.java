package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;
import main.java.org.daedalus.math.Vector2f;
import main.java.org.daedalus.math.Vector3f;

/**
 * Created by Stuart on 24/01/2017.
 */
public class Transform extends Component {

    public Vector3f position;
    public float rotation;
    public Vector2f scale;
    
    public Transform(){
        super(true);
        position = new Vector3f();
        scale = Vector2f.One();
    }
    
    
    
}
