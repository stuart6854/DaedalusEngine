package main.java.org.daedalus.tests;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;
import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.architecture.components.*;
import main.java.org.daedalus.graphics.types.Sprite;
import main.java.org.daedalus.graphics.types.Texture2D;
import main.java.org.daedalus.input.Keyboard;
import main.java.org.daedalus.math.Rect;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.Time;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by Stuart on 26/01/2017.
 */
public class SceneTest extends Scene {

    private Texture2D texture;
    private Sprite sprite;
    private GameObject boxA;
    private GameObject boxB;
    private BoxCollider colliderA;
    private BoxCollider colliderB;

    public SceneTest() {
        super("Scene Test 1");
    }

    @Override
    public void Initialise() {
        texture = Texture2D.Create("resources/textures/placeholder_orange_64.png");
        sprite = Sprite.Create(texture, new Rect(0, 0, texture.getWidth(), texture.getHeight()));
        boxA = new GameObject();
        boxA.name = "Box A";
        boxA.getTransform().position.set(0, 0, 0);
        SpriteRenderer spriteRenderer = boxA.AddComponent(new SpriteRenderer());
        spriteRenderer.SetSprite(sprite);
        colliderA = boxA.AddComponent(new BoxCollider());
        colliderA.SetSize(64, 64);
        AddGameObject(boxA);
    
        boxB = new GameObject();
        boxB.name = "Box B";
        boxB.getTransform().position.set(100, 100, 0);
        spriteRenderer = boxB.AddComponent(new SpriteRenderer());
        spriteRenderer.SetSprite(sprite);
        colliderB = boxB.AddComponent(new BoxCollider());
        colliderB.SetSize(64, 64);
        AddGameObject(boxB);
    
        Renderer.RENDER_MESH = true;
    }

    @Override
    public void Update() {
        boxB.getTransform().rotation.add(0, 0, 5 * Time.getDeltaTime());
        
        if(colliderA.CollidesWith(colliderB))
            Debug.Log("COLLIDES");
        
        if(Keyboard.isClicked(Keyboard.KEY_F1))
            SceneManager.LoadScene(1);

        Vector3f movement = new Vector3f();

        if(Keyboard.isPressed(Keyboard.KEY_W)) {
            movement.y += 50;
        }

        if(Keyboard.isPressed(Keyboard.KEY_S)) {
            movement.y += -50;
        }

        if(Keyboard.isPressed(Keyboard.KEY_A)) {
            movement.x += -50;
        }

        if(Keyboard.isPressed(Keyboard.KEY_D)) {
            movement.x += 50;
        }

        Transform transform = (Transform) boxA.GetComponent(Transform.class);
        transform.position.add(movement.mul(Time.getDeltaTime()));
    }

}
