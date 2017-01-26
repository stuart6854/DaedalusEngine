package main.java.org.daedalus.graphics.types;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

/**
 * Created by Stuart on 26/01/2017.
 */
public class Texture2D extends Texture {
    
    protected Texture2D(String _path) {
        super(_path);
    }
    
    @Override
    protected void UploadTexture(String _path) {
        glBindTexture(GL_TEXTURE_2D, id);
    
        // Wrapping
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
    
        //Filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        
        //TODO: Possible -Optional- MipMapping
    
        MemoryStack stack = MemoryStack.stackPush();
        IntBuffer w = stack.mallocInt(1);
        IntBuffer h = stack.mallocInt(1);
        IntBuffer comp = stack.mallocInt(1);
    
        stbi_set_flip_vertically_on_load(true); // true = Flip origin to bottom left, false = top left
        
        ByteBuffer image = stbi_load(_path, w, h, comp, 4);
        if(image == null){
            throw new RuntimeException("Failed to load texture file: " + stbi_failure_reason());
        }
        
        width = w.get();
        height = h.get();
        
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
    }
    
    public static Texture2D Create(String _path){
        return new Texture2D(_path);
    }
    
}
