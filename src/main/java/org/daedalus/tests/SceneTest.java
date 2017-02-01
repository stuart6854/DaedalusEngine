package main.java.org.daedalus.tests;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;
import main.java.org.daedalus.architecture.components.*;
import main.java.org.daedalus.graphics.types.MeshData;
import main.java.org.daedalus.graphics.types.Sprite;
import main.java.org.daedalus.graphics.types.Texture2D;
import main.java.org.daedalus.input.Keyboard;
import main.java.org.daedalus.math.Rect;
import main.java.org.daedalus.utils.Debug;
import main.java.org.daedalus.utils.MeshUtils;
import main.java.org.daedalus.utils.Time;
import org.joml.Vector2f;

/**
 * Created by 1622565 on 26/01/2017.
 */
public class SceneTest extends Scene {

    private Texture2D texture;
    private Sprite sprite;
    private GameObject testGameObject;

    public SceneTest(String _name) {
        super(_name);
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
        
//        BoxCollider collider = (BoxCollider)testGameObject.AddComponent(new BoxCollider());
//        collider.SetSize(64, 64);
        
        CircleCollider collider = (CircleCollider) testGameObject.AddComponent(new CircleCollider());
        collider.SetRadius(32);
        collider.SetCentre(32, 32);
    
        Renderer.RENDER_MESH = true;
    }

    @Override
    public void Update() {
        if(Keyboard.isPressed(Keyboard.KEY_W)) {
            Transform transform = (Transform)testGameObject.GetComponent(Transform.class);
            transform.position.add(0, 50 * Time.getDeltaTime(), 0);
        }
    }


}
