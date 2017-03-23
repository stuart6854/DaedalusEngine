package main.java.org.daedalus.tests;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;
import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.architecture.components.BoxCollider;
import main.java.org.daedalus.architecture.components.Renderer;
import main.java.org.daedalus.architecture.components.SpriteRenderer;
import main.java.org.daedalus.architecture.components.Transform;
import main.java.org.daedalus.graphics.types.Sprite;
import main.java.org.daedalus.graphics.types.Texture2D;
import main.java.org.daedalus.input.Keyboard;
import main.java.org.daedalus.math.Rect;
import main.java.org.daedalus.utils.Time;
import org.joml.Vector3f;

/**
 * Created by 1622565 on 26/01/2017.
 */
public class SceneTestTwo extends Scene {

    private Texture2D texture;
    private Sprite sprite;
    private GameObject testGameObject;

    public SceneTestTwo() {
        super("Scene Test 2");
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
            SceneManager.LoadScene(0);

        Transform transform = (Transform)testGameObject.GetComponent(Transform.class);
        transform.position.add(new Vector3f(25 * Time.getDeltaTime(), 0, 0));
    }


}
