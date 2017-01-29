package main.java.org.daedalus.graphics.types;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;

/**
 * Created by Stuart on 26/01/2017.
 */
public abstract class Texture {
    
    
    protected final int id;
    protected int textureUnit;
    
    protected int width, height;
    
    protected Texture(String _path){
        id = glGenTextures();
        UploadTexture(_path);
    }
    
    protected abstract void UploadTexture(String _path);
    
    public void Bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public int getId(){
        return id;
    }
    
    public int getTextureUnit(){
        return textureUnit;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
}
