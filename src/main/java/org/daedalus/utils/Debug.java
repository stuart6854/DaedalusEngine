package main.java.org.daedalus.utils;

import main.java.org.daedalus.architecture.components.Camera;
import main.java.org.daedalus.architecture.components.Transform;
import main.java.org.daedalus.graphics.shaders.ShaderProgram;
import main.java.org.daedalus.graphics.types.RenderModel;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLineWidth;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Debug {
        
    public static ShaderProgram DEBUG_MESH_SHADERPROGRAM;
    static {
        String vertexSource = FileUtils.ReadTextFile("resources/shaders/meshOutline_vertex.glsl");
        String fragmentSource = FileUtils.ReadTextFile("resources/shaders/meshOutline_fragment.glsl");
        DEBUG_MESH_SHADERPROGRAM = ShaderProgram.Create(vertexSource, fragmentSource);
    }
    
    public static void Log(Object _message){
        System.out.println(_message);
    }

    public static void Error(Object _error){
        System.err.println("Error: " + _error);
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        for(int i = 1; i < stackTraces.length; i++){
            StackTraceElement stackTrace = stackTraces[i];
            System.err.println("\t" + stackTrace.getClassName() + "." + stackTrace.getMethodName() + "():" + stackTrace.getLineNumber());
        }
    }
    
    public static void RenderMeshOutline(RenderModel _rModel, Transform _transform, int _indicesCount){
        glLineWidth(2f);
        DEBUG_MESH_SHADERPROGRAM.Bind();
    
        DEBUG_MESH_SHADERPROGRAM.SetUniform("projection", Camera.getMainCamera().getProjection());
        DEBUG_MESH_SHADERPROGRAM.SetUniform("view", Camera.getMainCamera().getView());
    
        DEBUG_MESH_SHADERPROGRAM.SetUniform("model", _transform.getTransformMatrix().translate(0, 0, 1));
        
        _rModel.PrepareRender();
        
        glDrawElements(GL_LINE_LOOP, _indicesCount, GL_UNSIGNED_INT, 0);
        
        //Restore Line Width
        glLineWidth(1f);
    }

}
