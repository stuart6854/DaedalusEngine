package main.java.org.daedalus.tests;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;

/**
 * Created by 1622565 on 26/01/2017.
 */
public class SceneTest extends Scene {

    private GameObject testGameObject;

    public SceneTest(String _name) {
        super(_name);
    }

    @Override
    public void Initialise() {
        testGameObject = new GameObject();
        testGameObject.name = "GameObject 1";
    }

    @Override
    public void Update() {

    }


}
