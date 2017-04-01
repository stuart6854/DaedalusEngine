package main.java.org.daedalus.tests;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;
import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.architecture.components.*;
import main.java.org.daedalus.graphics.types.Sprite;
import main.java.org.daedalus.graphics.types.Texture2D;
import main.java.org.daedalus.input.Keyboard;
import main.java.org.daedalus.math.Rect;
import main.java.org.daedalus.utils.Time;
import org.joml.Vector3f;

/**
 * Created by 1622565 on 26/01/2017.
 */
public class SceneTest extends Scene {

    private Texture2D texture;
    private Sprite sprite;
    private GameObject testGameObject;

    public SceneTest() {
        super("Scene Test 1");
    }

    @Override
    public void Initialise() {
        texture = Texture2D.Create("resources/textures/placeholder_orange_64.png");
        sprite = Sprite.Create(texture, new Rect(0, 0, texture.getWidth(), texture.getHeight()));
        testGameObject = new GameObject();
        testGameObject.name = "GameObject 1";
        testGameObject.getTransform().position.set(10, 100, 0);
        AddGameObject(testGameObject);
        
        SpriteRenderer spriteRenderer = testGameObject.AddComponent(new SpriteRenderer());
        spriteRenderer.SetSprite(sprite);
        
//        testGameObject.AddComponent(new Rigidbody());
        
        BoxCollider collider = (BoxCollider)testGameObject.AddComponent(new BoxCollider());
        collider.SetSize(64, 64);
        
//        CircleCollider collider = (CircleCollider) testGameObject.AddComponent(new CircleCollider());
//        collider.SetRadius(32);
//        collider.SetCentre(32, 32);
    
        Renderer.RENDER_MESH = true;
    }

    @Override
    public void Update() {
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

        Transform transform = (Transform)testGameObject.GetComponent(Transform.class);
        transform.position.add(movement.mul(Time.getDeltaTime()));
    }


}
