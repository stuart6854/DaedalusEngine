package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;
import main.java.org.daedalus.graphics.Sprite;
import main.java.org.daedalus.graphics.types.Color;

/**
 * Created by Stuart on 26/01/2017.
 */
public class SpriteRenderer extends Component {
        
    private Sprite sprite; // The sprite to render.
    private Color color; // The color to render this sprite. (additive)
    //private boolean flipX, flipY; // Axes to flip the sprite on when rendering.
    
    public SpriteRenderer() {
        super(true);
    }
    
}
