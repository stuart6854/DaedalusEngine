package main.java.org.daedalus.tests;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;
import main.java.org.daedalus.graphics.types.Texture2D;

/**
 * Created by 1622565 on 26/01/2017.
 */
public class SceneTest extends Scene {

    private Texture2D texture;
    private GameObject testGameObject;

    public SceneTest(String _name) {
        super(_name);
    }

    @Override
    public void Initialise() {
        texture = Texture2D.Create("resources/textures/placeholder_orange_64.png");
        testGameObject = new GameObject();
        testGameObject.name = "GameObject 1";
    }

    @Override
    public void Update() {

    }


}
