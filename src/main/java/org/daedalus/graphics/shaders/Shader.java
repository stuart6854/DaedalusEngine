package main.java.org.daedalus.graphics.shaders;

import com.sun.xml.internal.bind.v2.model.core.ID;
import main.java.org.daedalus.utils.FileUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    
    public static final int VERTEX_SHADER = GL_VERTEX_SHADER, FRAGMENT_SHADER = GL_FRAGMENT_SHADER;
    
    private int shaderId;         // The Id of the shader. Used to bind to a ShaderProgram.
    private int shaderType;       // The type of shader. VERTEX_SHADER or FRAGMENT_SHADER.
    
    private String shaderSource;
    private boolean isCompiled;
    
    protected Shader(String _sourceCode, int _shaderType){
        shaderSource = _sourceCode;
        shaderType = _shaderType;
    }
    
    private void Compile(){
        if(isCompiled)
            return;
        
        shaderId = glCreateShader(shaderType);
        if(shaderId == 0)
            throw new RuntimeException("Could not create shader!");
        
        glShaderSource(shaderId, shaderSource);
        glCompileShader(shaderId);
        
        if(glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0)
            throw new RuntimeException("Failed to compile shader: " + glGetShaderInfoLog(shaderId));
        
        isCompiled = true;
    }
    
    public int getShaderId() {
        return shaderId;
    }
    
    public int getShaderType() {
        return shaderType;
    }
    
    public String getShaderSource() {
        return shaderSource;
    }
    
    public boolean isCompiled() {
        return isCompiled;
    }
    
    public static Shader CreateShader(String _sourceCode, int _shaderType){
        Shader shader = new Shader(_sourceCode, _shaderType);
        shader.Compile();

        return shader;
    }
    
}
