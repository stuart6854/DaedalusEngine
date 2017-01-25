package main.java.org.daedalus.core;

import main.java.org.daedalus.graphics.Window;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.Time;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Configuration;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Engine {

    private Time time;
    private boolean isRunning;
    
    private Window window;
    
    public static void main(String... args){
        Engine engine = new Engine();
        engine.Initialise();
        engine.Start();
    }

    public void Initialise(){
        //Initialise OpenGL & GLFW
        Configuration.DEBUG.set(true);
        GLFW.glfwInit();
        
        //Initialise Engine
        window = new Window();
        window.InitWindow("Daedalus Engine", 640, 480, false);
        
        time = new Time();
    }
    
    public void Start(){
        window.CreateWindow();
        Debug.Log("LWJGL Version " + Version.getVersion());
        EngineLoop();
    }
    
    private void EngineLoop(){
        isRunning = true;

        double lastTime = GLFW.glfwGetTime();
        while(isRunning){
            double currentTime = GLFW.glfwGetTime();
            time.setDeltaTime(lastTime - currentTime);
            lastTime = currentTime;

            //TODO: Update
            //TODO: Render
            
            window.Update();
            
            if(window.shouldClose()) isRunning = false;
        }
        Cleanup();
    }

    private void Cleanup(){
        window.Destroy();
    }

}
