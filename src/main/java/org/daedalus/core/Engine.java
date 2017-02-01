package main.java.org.daedalus.core;

import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.graphics.types.Color;
import main.java.org.daedalus.graphics.Window;
import main.java.org.daedalus.graphics.ui.GUI;
import main.java.org.daedalus.input.Keyboard;
import main.java.org.daedalus.input.Mouse;
import main.java.org.daedalus.physics.Physics;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.Time;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Configuration;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Engine {

    private Time time;
    private boolean isRunning;
    
    private Window window;
    private Physics physics;
    private GUI gui;
    
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
        time = new Time();
        
        window = new Window();
        window.InitWindow("Daedalus Engine", 1280, 720, false);
        window.CreateWindow();
        
        physics = new Physics();
        physics.Initialise();
        
        gui = new GUI();
        gui.Initialise();
    }
    
    public void Start(){

        Debug.Log("LWJGL Version " + Version.getVersion());
        Debug.Log("OpenGL Version: " + glGetString(GL_VERSION));
        Debug.Log("GLFW Version " + GLFW.glfwGetVersionString());

        window.SetClearColor(Color.Black());
        
        SceneManager.Initialise();
        
        EngineLoop();
    }
    
    private void EngineLoop(){
        isRunning = true;

        double lastTime = GLFW.glfwGetTime();
        while(isRunning){
            double currentTime = GLFW.glfwGetTime();
            time.setDeltaTime((currentTime - lastTime)); // Convert to ms
            lastTime = currentTime;

            physics.Tick();
            Update();
            Render();

            window.SetTitle("Daedalus Engine " + (Time.getDeltaTime() * 1000d) + "ms");
            window.Update();
            
            if(window.shouldClose()) isRunning = false;
        }

        Cleanup();
    }

    private void Update(){
        Mouse.BeginFrame(Time.getDeltaTime());
        Keyboard.BeginFrame();
        
        SceneManager.Update();
        
        Mouse.EndFrame();
        Keyboard.EndFrame();
    }
    
    private void Render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
        SceneManager.Render();
        
        gui.Render();
    }
    
    private void Cleanup(){
        window.Destroy();
        gui.Cleanup();
    }

}
