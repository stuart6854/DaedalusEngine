package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.graphics.shaders.ShaderProgram;
import main.java.org.daedalus.graphics.types.Sprite;
import main.java.org.daedalus.graphics.types.Color;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.FileUtils;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Stuart on 26/01/2017.
 */
public class SpriteRenderer extends Renderer {
        
    public static ShaderProgram spriteProgram;
    static {
        String vertexSource = FileUtils.ReadTextFile("resources/shaders/vertex.glsl");
        String fragmentSource = FileUtils.ReadTextFile("resources/shaders/fragment.glsl");
        spriteProgram = ShaderProgram.Create(vertexSource, fragmentSource);
    }
    
    private Sprite sprite; // The sprite to render.
    private Color color; // The color to render this sprite. (additive)
    //private boolean flipX, flipY; // Axes to flip the sprite on when rendering.
    
    public SpriteRenderer() {
        
    }
    
    @Override
    public void Render() {
        super.Render();
        
        glEnable(GL_DEPTH_TEST);
        
        spriteProgram.Bind();
        sprite.getTexture().Bind();
        
        spriteProgram.SetUniform("projection", Camera.getMainCamera().getProjection());
        spriteProgram.SetUniform("view", Camera.getMainCamera().getView());
        
        spriteProgram.SetUniform("model", transform.getTransformMatrix());
        
        sprite.getRenderModel().PrepareRender();
        
        glDrawElements(GL_TRIANGLES, sprite.getTriangles().length, GL_UNSIGNED_INT, 0);
        
//        if(RENDER_MESH)
//            Debug.RenderMeshOutline(sprite.getRenderModel(), transform, sprite.getTriangles().length);
    }
    
    public void SetSprite(Sprite _sprite){
        sprite = _sprite;
    }
    
    public void SetColor(Color _color){
        color = _color;
    }
    
}
