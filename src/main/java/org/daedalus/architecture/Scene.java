package main.java.org.daedalus.architecture;

import main.java.org.daedalus.architecture.components.Camera;
import main.java.org.daedalus.utils.Debug;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Stuart on 24/01/2017.
 */
public class Scene {

    protected String name;
    
    private Camera mainCamera;
    protected List<GameObject> gameObjects = new ArrayList<>();

    public Scene(String _name){
        name = _name;
        
        getMainCamera(); // Cheat to create a MainCamera
    }

    public void Initialise(){
    }

    public void Update(){
        for(GameObject gameObject : gameObjects)
            gameObject.Update();
            
    }
    
    public void Render(){
        for(GameObject gameObject : gameObjects)
            gameObject.Render();
    }

    public void Cleanup(){

    }
    
    public void AddGameObject(GameObject _gameObject){
        if(gameObjects.contains(_gameObject)){
            Debug.Error("GameObject " + _gameObject.name + " is already registered with scene " + name + "!");
            return;
        }
        
        gameObjects.add(_gameObject);
    }
    
    public void SetMainCamera(Camera _camera){
        mainCamera = _camera;
    }
    
    public String getName(){
        return name;
    }
    
    public Camera getMainCamera(){
        if(mainCamera == null){
            GameObject cam = new GameObject("Main Camera");
            mainCamera = cam.AddComponent(new Camera());
            AddGameObject(cam);
        }
        
        return mainCamera;
    }
    
}
