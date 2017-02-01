package main.java.org.daedalus.tests;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;
import main.java.org.daedalus.architecture.components.Rigidbody;
import main.java.org.daedalus.architecture.components.SpriteRenderer;
import main.java.org.daedalus.architecture.components.Transform;
import main.java.org.daedalus.graphics.types.Sprite;
import main.java.org.daedalus.graphics.types.Texture2D;
import main.java.org.daedalus.input.Keyboard;
import main.java.org.daedalus.math.Rect;
import main.java.org.daedalus.utils.Time;

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
        SpriteRenderer spriteRenderer = testGameObject.AddComponent(new SpriteRenderer());
        spriteRenderer.SetSprite(sprite);
        testGameObject.AddComponent(new Rigidbody());
        AddGameObject(testGameObject);
        
        testGameObject.getTransform().position.set(10, 100, 0);
    }

    @Override
    public void Update() {
        if(Keyboard.isPressed(Keyboard.KEY_W)) {
            Transform transform = (Transform)testGameObject.GetComponent(Transform.class);
            transform.position.add(0, 50 * Time.getDeltaTime(), 0);
        }
    }


}
