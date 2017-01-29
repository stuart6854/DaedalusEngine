package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;
import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.graphics.Resolution;
import main.java.org.daedalus.graphics.Window;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by Stuart on 27/01/2017.
 */
public class Camera extends Component {
    
    private float orthographicSize = 100f; // Half of the camera orthographic height.
    
    private Matrix4f projection;
    
    public Camera() {
        super(true);
    }
    
    public void SetMainCamera(Camera _camera){
        SceneManager.getCurrentScene().SetMainCamera(_camera);
    }
    
    public Matrix4f getProjection(){
        if(projection == null) {
            Resolution res = Window.getResolution();
            float aspect = res.getAspectRatio();
            float height = orthographicSize * 2;
            projection = new Matrix4f().ortho2D(0, (int)(height * aspect), 0, height);
        }
        
        return projection;
    }
    
    public Matrix4f getView(){
        Matrix4f view = new Matrix4f();
        view.rotate((float) Math.toRadians(transform.rotation.x), new Vector3f(1, 0, 0))
                .rotate((float)Math.toRadians(transform.rotation.y), new Vector3f(0, 1, 0));
        view.translate(-transform.position.x, -transform.position.y, -transform.position.z);
        
        return view;
    }
    
    public static Camera getMainCamera(){
        return SceneManager.getCurrentScene().getMainCamera();
    }
    
}
