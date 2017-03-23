package main.java.org.daedalus.tests.sokoban;

import main.java.org.daedalus.architecture.SceneManager;
import main.java.org.daedalus.core.Engine;

/**
 * Created by 1622565 on 23/03/2017.
 */
public class Sokoban {

    public static void main(String... _args){
        SceneManager.AddScene(new Level1());

        Engine e = new Engine();
        e.Initialise();
        e.Start();
    }

}
