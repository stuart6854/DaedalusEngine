package main.java.org.daedalus.graphics.types;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

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
    
}
