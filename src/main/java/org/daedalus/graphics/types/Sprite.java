package main.java.org.daedalus.graphics.types;

/**
 * Created by Stuart on 26/01/2017.
 */

import main.java.org.daedalus.math.Rect;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Defines what part of a texture is to be rendered for a sprite
 */
public class Sprite {
    
    private Texture texture;            // The texture the sprite is to be taken from.
    private boolean packed;             // Is this sprite packed in an atlas.
    private Rect rect;                  // The location of the sprite in the texture.
    
    private int[] triangles;            // Sprite Mesh Triangles
    private Vector2f[] uv;              // Sprite Mesh UVs
    private Vector3f[] vertices;        // Sprite Mesh Vertices
    
    private RenderModel renderModel;    // Holds to refernce to the VAO/VBOs stored on the GPU.
    
    protected Sprite(Texture _texture, Rect _rect){
        texture = _texture;
        rect = _rect;
    }
    
    public static Sprite Create(Texture _texture, Rect _rect){
        Sprite sprite = new Sprite(_texture, _rect);
//        sprite.CreateSquareMesh(10, 10);
        sprite.CreateSquareMesh((int)_rect.getWidth(), (int)_rect.getHeight());
        return sprite;
    }
    
    private void CreateSquareMesh(int _width, int _height){
        vertices = new Vector3f[]{
                new Vector3f(0, 0, 0),
                new Vector3f(_width, 0, 0),
                new Vector3f(_width, _height, 0),
                new Vector3f(0, _height, 0)
        };
        
        triangles = new int[]{
                0, 1, 3,
                1, 2, 3
        };
        
        uv = new Vector2f[]{
                new Vector2f(0, 0),
                new Vector2f(1, 0),
                new Vector2f(1, 1),
                new Vector2f(0, 1)
        };
        
        renderModel = RenderModel.CreateModel(triangles, vertices, uv);
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public boolean isPacked() {
        return packed;
    }
    
    public Rect getRect() {
        return rect;
    }
    
    public int[] getTriangles() {
        return triangles;
    }
    
    public Vector2f[] getUv() {
        return uv;
    }
    
    public Vector3f[] getVertices() {
        return vertices;
    }
    
    public RenderModel getRenderModel(){
        return renderModel;
    }
    
}
