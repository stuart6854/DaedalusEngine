package main.java.org.daedalus.graphics.shaders;

import org.joml.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Stuart on 27/01/2017.
 */
public class ShaderProgram {
    
    private int programId;
    
    private Shader vertexShader;
    private Shader fragmentShader;
    
    private final Map<String, Integer> uniforms;
    
    private boolean isInitialised;
    
    protected ShaderProgram(){
        uniforms = new HashMap<>();
    }
    
    protected void Initialise(){
        programId = glCreateProgram();
        if(programId == 0)
            throw new RuntimeException("Failed to create ShaderProgram!");
        
        isInitialised = true;
    }
    
    protected void AttachShader(Shader shader){
        if(!isInitialised)
            return;
        
        if(shader.getShaderType() == Shader.VERTEX_SHADER)
            vertexShader = shader;
        else if(shader.getShaderType() == Shader.FRAGMENT_SHADER)
            fragmentShader = shader;
        
        if(shader.isCompiled())
            glAttachShader(programId, shader.getShaderId());
    }
    
    protected void Link(){
        if(!isInitialised)
            return;
        
        glLinkProgram(programId);
        if(glGetProgrami(programId, GL_LINK_STATUS) == 0)
            throw new RuntimeException("Failed to link ShaderProgram: " + glGetShaderInfoLog(programId));
        
        glValidateProgram(programId);
        if(glGetProgrami(programId, GL_VALIDATE_STATUS) == 0)
            throw new RuntimeException("Failed to validate ShaderProgram: " + glGetShaderInfoLog(programId));
    }
    
    public void Bind(){
        if(!isInitialised)
            return;
        
        glUseProgram(programId);
    }
    
    public int GetUniform(String name){
        Bind();
        
        if(uniforms.containsKey(name))
            return uniforms.get(name);
        
        int location = glGetUniformLocation(programId, name);
        if(location == -1)
            throw new RuntimeException("Could not find Uniform: " + name);
    
        uniforms.put(name, location);
        
        return location;
    }
    
    public void SetUniform(int location, int[] values){
        if(values.length > 4)
            throw new RuntimeException("Uniform component can not have more than 4 components!");
        
        Bind();
        
        switch(values.length){
            case 1:
                glUniform1i(location, values[0]);
                break;
            case 2:
                glUniform2i(location, values[0], values[1]);
                break;
            case 3:
                glUniform3i(location, values[0], values[1], values[2]);
                break;
            case 4:
                glUniform4i(location, values[0], values[1], values[2], values[3]);
                break;
        }
    }
    
    public void SetUniform(String location, int[] values){
        SetUniform(GetUniform(location), values);
    }
    
    public void SetUniform(int location, float[] values){
        if(values.length > 4)
            throw new RuntimeException("Uniform component can not have more than 4 components!");
        
        Bind();
        
        switch(values.length){
            case 1:
                glUniform1f(location, values[0]);
                break;
            case 2:
                glUniform2f(location, values[0], values[1]);
                break;
            case 3:
                glUniform3f(location, values[0], values[1], values[2]);
                break;
            case 4:
                glUniform4f(location, values[0], values[1], values[2], values[3]);
                break;
        }
    }
    
    public void SetUniform(String location, float[] values){
        SetUniform(GetUniform(location), values);
    }
    
    public void SetUniform(String name, int value){
        SetUniform(name, new int[] { value });
    }
    
    public void SetUniform(String name, float value){
        SetUniform(name, new float[] { value });
    }
    
    public void SetUniform(String name, Vector3f vector){
        SetUniform(name, new float[]{ vector.x, vector.y, vector.z });
    }
    
    public void SetUniform(String name, Vector3d vector){
        SetUniform(name, new float[]{ (float)vector.x, (float)vector.y, (float)vector.z });
    }
    
    public void SetUniform(String name, Vector4f vector){
        SetUniform(name, new float[]{ vector.x, vector.y, vector.z, vector.w});
    }
    
    public void SetUniform(String name, Vector4d vector){
        SetUniform(name, new float[]{ (float)vector.x, (float)vector.y, (float)vector.z, (float)vector.w });
    }
    
    public void SetUniform(String name, Matrix4d value){
        Bind();
        
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        glUniformMatrix4fv(GetUniform(name), false, value.get(buffer));
    }
    
    public void SetUniform(String name, Matrix4f value){
        Bind();
        
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        glUniformMatrix4fv(GetUniform(name), false, value.get(buffer));
    }
        
    public void Cleanup(){
        if(programId != 0){
            if(vertexShader.getShaderId() != 0)
                glDetachShader(programId, vertexShader.getShaderId());
            
            if(fragmentShader.getShaderId() != 0)
                glDetachShader(programId, fragmentShader.getShaderId());
            
            glDeleteProgram(programId);
        }
        uniforms.clear();
    }
        
    public static ShaderProgram Create(String _vertexSource, String _fragmentSource){
        ShaderProgram program = new ShaderProgram();
        program.Initialise();
        
        program.AttachShader(Shader.CreateShader(_vertexSource, Shader.VERTEX_SHADER));
        program.AttachShader(Shader.CreateShader(_fragmentSource, Shader.FRAGMENT_SHADER));
        
        program.Link();
        return program;
    }
    
}
