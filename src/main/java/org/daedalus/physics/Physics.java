package main.java.org.daedalus.physics;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;
import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.architecture.components.Rigidbody;
import main.java.org.daedalus.utils.Time;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Stuart on 29/01/2017.
 */
public class Physics {
    
    private static Vector3f gravity = new Vector3f(0, -9.8f, 0); // Earths gravity is 9.8m/s
    
    public static World world; // JBox2D World
    
    private float accumulator = 0.0f;
    private float frameStart;
    
    private int velocityIterations = 6;
    private int positionIteration = 2;
    
    public void Initialise(){
        frameStart = (float)glfwGetTime();
        
        world = new World(new Vec2());
        SetGravity(gravity);
    }
    
    public void Tick(){
        float currentTime = (float)glfwGetTime();
        accumulator += currentTime - frameStart;
        frameStart = currentTime;
        
        //Prevent spiral of death by clamping dt.
        if(accumulator > 0.2f)
            accumulator = 0.2f;
        
        while(accumulator > Time.getFixedDeltaTime()){
            UpdatePhysics(Time.getFixedDeltaTime());
            accumulator -= Time.getDeltaTime();
        }
    }
    
    private void UpdatePhysics(float _dt){
        world.step(Time.getFixedDeltaTime(), velocityIterations, positionIteration);
        
//        Scene scene = SceneManager.getCurrentScene();
//        for(GameObject gameObject : scene.getGameObjects()){
//            Rigidbody rbody = (Rigidbody)gameObject.GetComponent(Rigidbody.class);
//            if(rbody != null)
//                rbody.TickPhysics(_dt);
//        }
    }
    
    public static void SetGravity(Vector3f _gravity){
        gravity = _gravity;
        world.setGravity(new Vec2(gravity.x, gravity.y));
    }
    
    public static Vector3f getGravity(){
        return gravity;
    }
    
}
