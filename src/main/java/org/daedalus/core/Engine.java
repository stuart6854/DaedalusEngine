package main.java.org.daedalus.core;

import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.graphics.Color;
import main.java.org.daedalus.graphics.Window;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.Time;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Configuration;

import java.text.DecimalFormat;

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

        SceneManager.Initialise();

        time = new Time();
    }
    
    public void Start(){
        window.CreateWindow();

        Debug.Log("LWJGL Version " + Version.getVersion());
        Debug.Log("GLFW Version " + GLFW.glfwGetVersionString());

        window.SetClearColor(Color.red);

        EngineLoop();
    }
    
    private void EngineLoop(){
        isRunning = true;

        double lastTime = GLFW.glfwGetTime();
        while(isRunning){
            double currentTime = GLFW.glfwGetTime();
            time.setDeltaTime((currentTime - lastTime) * 1000d); // Convert to ms
//            Debug.Log(Time.getDeltaTime());
            lastTime = currentTime;

            //TODO: Update
            Update();
            //TODO: Render

            window.SetTitle("Daedalus Engine " + Time.getDeltaTime() + "ms");
            window.Update();
            
            if(window.shouldClose()) isRunning = false;
        }

        Cleanup();
    }

    private void Update(){
        SceneManager.Update();
    }

    private void Cleanup(){
        window.Destroy();
    }

}
