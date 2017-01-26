package main.java.org.daedalus.graphics;

/**
 * Created by Stuart on 26/01/2017.
 */

import main.java.org.daedalus.graphics.types.Texture;
import main.java.org.daedalus.math.Rect;

/**
 * Defines what part of a texture is to be rendered for a sprite
 */
public class Sprite {
    
    private Texture texture; // The texture the sprite is to be taken from.
    private boolean packed; // Is this sprite packed in an atlas.
    private Rect rect; // The location of the sprite in the texture.
    
    protected Sprite(Texture _texture, Rect _rect){
        texture = _texture;
        rect = _rect;
    }
    
    public static Sprite Create(Texture _texture, Rect _rect){
        Sprite sprite = new Sprite(_texture, _rect);
        
        return sprite;
    }
    
}
