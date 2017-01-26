package main.java.org.daedalus.architecture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stuart on 24/01/2017.
 */
public class Scene {

    protected String name;

    protected List<GameObject> gameObjects = new ArrayList<>();

    public Scene(String _name){
        name = _name;
    }

    public void Initialise(){

    }

    public void Update(){

    }

    public void Cleanup(){

    }

    public String getName(){
        return name;
    }

}
