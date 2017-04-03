package main.java.org.daedalus.graphics.types;

/**
 * Created by Stuart on 26/01/2017.
 */

import main.java.org.daedalus.math.Math;
import main.java.org.daedalus.math.Rect;
import main.java.org.daedalus.utils.Debug;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Defines what part of a texture is to be rendered for a sprite
 */
public class Sprite {
    
    private Texture texture;            // The texture the sprite is to be taken from.
    private boolean packed;             // Is this sprite packed in an atlas.
    private Rect rect;                  // The location of the sprite in the texture.
    
    private Vector2f pivot;             // Normalised Pivot Coords
    
    private int[] triangles;            // Sprite Mesh Triangles
    private Vector2f[] uv;              // Sprite Mesh UVs
    private Vector3f[] vertices;        // Sprite Mesh Vertices
    
    private RenderModel renderModel;    // Holds to reference to the VAO/VBOs stored on the GPU.
    
    protected Sprite(Texture _texture, Rect _rect, Vector2f _pivot){
        texture = _texture;
        rect = _rect;
        pivot = _pivot;
    }
    
    public static Sprite Create(Texture _texture, Rect _rect){
        return Create(_texture, _rect, new Vector2f(0.5f, 0.5f));
    }
    
    public static Sprite Create(Texture _texture, Rect _rect, Vector2f _pivot){
        if(_pivot.x < 0 || _pivot.x > 1 || _pivot.y < 0 || _pivot.y > 1) {
            Debug.Warning("Sprite Pivot must be Normalised Coords, eg. 0-1. Clamping Pivot!");
            _pivot.x = Math.Clamp01(_pivot.x);
            _pivot.y = Math.Clamp01(_pivot.y);
        }
        
        Sprite sprite = new Sprite(_texture, _rect, _pivot);
        sprite.CreateSquareMesh((int)_rect.getWidth(), (int)_rect.getHeight(), _pivot);
        return sprite;
    }
    
    private void CreateSquareMesh(int _width, int _height, Vector2f _pivot){
        float minX, maxX;
        float minY, maxY;
        
        minX = 0 - (_width * _pivot.x);
        maxX = _width - (_width * _pivot.x);
        minY = 0 - (_height * _pivot.y);
        maxY = _height - (_height * _pivot.y);
        
        vertices = new Vector3f[]{
                new Vector3f(minX, minY, 0),
                new Vector3f(maxX, minY, 0),
                new Vector3f(maxX, maxY, 0),
                new Vector3f(minX, maxY, 0)
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
    
    public Vector2f getPivot(){
        return pivot;
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
