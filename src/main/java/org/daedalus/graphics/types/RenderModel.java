package main.java.org.daedalus.graphics.types;

import main.java.org.daedalus.utils.Utils;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by Stuart on 27/01/2017.
 */
public class RenderModel {
    
    public static List<RenderModel> Models = new ArrayList<>();
    
    private final int VAO_ID;
    private final int INDEX_VBO_ID;
    private final int[] VBO_IDs;
    
    public RenderModel(int vao_id, int index_vbo_id, int[] vbos){
        this.VAO_ID = vao_id;
        this.INDEX_VBO_ID = index_vbo_id;
        this.VBO_IDs = vbos;
    }
    
    public void PrepareRender(){
        glBindVertexArray(VAO_ID);
        
        for(int vbo : VBO_IDs) {
            glEnableVertexAttribArray(vbo);
        }
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, INDEX_VBO_ID);
    }
    
    public void ReleaseModel(){
        glDeleteVertexArrays(VAO_ID);
        for(int vbo : VBO_IDs)
            glDeleteBuffers(vbo);
        
        glDeleteBuffers(INDEX_VBO_ID);
    }
    
    ///RENDER-MODEL FACTORY///
    
    private static boolean CREATING = false;
    
    private static int Current_Model = -1;
    private static int Current_VBO_Index = -1;
    private static int[] Current_VBOs = null;
    private static int Current_Index_VBO = -1;
    
    public static RenderModel CreateModel(int[] _indices, Vector3f[] _vertices, Vector2f[] _uvs){
        List<Float> verts = new ArrayList<Float>();
        for(Vector3f vert : _vertices){
            verts.add(vert.x);
            verts.add(vert.y);
            verts.add(vert.z);
        }
    
        List<Float> uvs = new ArrayList<>();
        for(Vector2f uv : _uvs){
            uvs.add(uv.x);
            uvs.add(uv.y);
        }
        
        return CreateModel(_indices, Utils.ToArrayFloat(verts), Utils.ToArrayFloat(uvs), null, null);
    }
    
    public static RenderModel CreateModel(int[] indices, float[] vertices, float[] uvs, float[] normals, float[] colors){
        if(CREATING){
            return null;
        }
        int ValidDataCount = ValidDataCount(vertices, uvs, normals, colors);
        
        CreateModel(ValidDataCount);
        
        SetupIndexBuffer(indices);
        SetupAttributeBuffer(0, 3, vertices);
        if(uvs != null && uvs.length > 0)
            SetupAttributeBuffer(1, 2, uvs);
        if(normals != null && normals.length > 0)
            SetupAttributeBuffer(2, 3, normals);
        if(colors != null && colors.length > 0)
            SetupAttributeBuffer(3, 4, colors);
        
        FinaliseModel();
        return StoreModel();
    }
    
    public static RenderModel CreateDebugRenderModel(float[] vertices, int[] indices){
        if(CREATING){
            return null;
        }
        
        CreateModel(1);
        SetupAttributeBuffer(0, 3, vertices);
        SetupIndexBuffer(indices);
        FinaliseModel();
        return StoreModel();
    }
    
    private static void CreateModel(int vbos){
        CREATING = true;
        Current_Model = glGenVertexArrays();
        Current_VBO_Index = -1;
        Current_VBOs = new int[vbos];
        for(int i = 0; i < vbos; i++) Current_VBOs[i] = -1;
        glBindVertexArray(Current_Model);
    }
    
    private static void SetupAttributeBuffer(int location, int components, float[] data){
        int vbo_id = glGenBuffers();
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        glBindBuffer(GL_ARRAY_BUFFER, vbo_id);
        buffer.put(data);
        buffer.flip();
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(location);
        glVertexAttribPointer(location, components, GL_FLOAT, false, 0, 0);
        
        Current_VBO_Index += 1;
        Current_VBOs[Current_VBO_Index] = vbo_id;
    }
    
    private static void SetupIndexBuffer(int[] indices){
        Current_Index_VBO = glGenBuffers();
        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, Current_Index_VBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }
    
    private static void FinaliseModel(){
        for(int vbo : Current_VBOs)
            glEnableVertexAttribArray(vbo);
        
        glEnableVertexAttribArray(Current_Index_VBO);
        
        glBindVertexArray(0);
    }
    
    private static RenderModel StoreModel(){
        RenderModel model = new RenderModel(Current_Model, Current_Index_VBO, Current_VBOs);
        CREATING = false;
        
        ResetStaticVars();
        return model;
    }
    
    private static void ResetStaticVars(){
        Current_Model = -1;
        Current_VBO_Index = -1;
        Current_VBOs = null;
    }
    
    private static void ReleaseModels(){
        for(RenderModel model : Models)
            model.ReleaseModel();
    }
    
    private static int ValidDataCount(float[] vertices, float[] uvs, float[] normals, float[] colors){
        int validData = 0;
        
        if(vertices != null)
            validData += 1;
        if(uvs != null)
            validData += 1;
        if(normals != null)
            validData += 1;
        if(colors != null)
            validData += 1;
        
        return validData;
    }
    
}
