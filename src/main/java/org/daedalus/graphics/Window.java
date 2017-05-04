package main.java.org.daedalus.graphics;

import main.java.org.daedalus.graphics.types.Color;
import main.java.org.daedalus.input.Keyboard;
import main.java.org.daedalus.input.Mouse;
import main.java.org.daedalus.utils.Debug;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Window {
    
    private static long windowId;
    
    private static String title;
    private static Resolution resolution;
    
    private GLFWKeyCallback keyCallback;
    
    public void InitWindow(String _title, int _width, int _height, boolean _fullscreen){
        InitWindow(_title, new Resolution(_width, _height, _fullscreen));
    }
    
    public void InitWindow(String _title, Resolution _resolution){
        title = _title;
        resolution = _resolution;
    }
    
    public void CreateWindow(){
        if(windowId != NULL){
            Debug.Error("Window has already been created!");
            return;
        }
        
        SetWindowHints();
        
        windowId = glfwCreateWindow(resolution.width, resolution.height, title, NULL, NULL);
        if(windowId == NULL)
            throw new RuntimeException("Failed to create window!");
        
        glfwMakeContextCurrent(windowId);
        
        GL.createCapabilities();
        SetGL();
    
        SetCallbacks();
        CentreOnScreen();
        SetVisible(true);
    }
    
    public void Update(){
        if(windowId == NULL) return;
        
        glfwPollEvents();
        glfwSwapBuffers(windowId);
    }
    
    public void Destroy(){
        glfwDestroyWindow(windowId);
        
        //TODO: Free callbacks
        keyCallback.free();
        
        glfwTerminate();
    }
    
    private void SetWindowHints(){
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_SAMPLES, 4); // Allow optional antialiasing
    }
    
    public static void SetGL(){
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_STENCIL_TEST);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glEnable(GL_ALPHA_TEST);
        glAlphaFunc(GL_GREATER, 0.1f);
    }
    
    private void SetCallbacks(){
        glfwSetKeyCallback(windowId, keyCallback = new Keyboard());
        glfwSetCharCallback(windowId, Keyboard.CharacterCallback);
        new Mouse().setCallbacks(windowId);
    }
    
    private void CentreOnScreen(){
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowId, (vidMode.width() - resolution.width) / 2, (vidMode.height() - resolution.height) / 2);
    }

    private static void SetVisible(boolean _visible){
        if(_visible) glfwShowWindow(windowId);
        else glfwHideWindow(windowId);
    }

    public static void SetTitle(String _title){
        glfwSetWindowTitle(windowId, _title);
    }

    public static void SetClearColor(Color _color){
        glClearColor(_color.getR(), _color.getG(), _color.getB(), _color.getA());
    }

    public boolean shouldClose(){
        return glfwWindowShouldClose(windowId);
    }
    
    public static long getWindowId(){
        return windowId;
    }
    
    public static String getTitle() {
        return title;
    }
    
    public static Resolution getResolution() {
        return resolution;
    }
    
}

