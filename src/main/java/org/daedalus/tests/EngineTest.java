package main.java.org.daedalus.tests;

import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.core.Engine;

/**
 * Created by 1622565 on 26/01/2017.
 */
public class EngineTest {

    public static void main(String... args){
        SceneManager.AddScene(new SceneTest("Test Scene"));

        Engine engine = new Engine();
        engine.Initialise();
        engine.Start();
    }

}
