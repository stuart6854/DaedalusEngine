package main.java.org.daedalus.architecture;

import main.java.org.daedalus.architecture.components.Camera;
import main.java.org.daedalus.utils.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stuart on 25/01/2017.
 */
public class SceneManager {

    private static Scene currentScene;

    private static List<Scene> scenes = new ArrayList<>();

    public static void Initialise(){
        if(scenes.size() == 0){
           throw new RuntimeException("No scenes in build!");
        }

        LoadScene(0);
    }

    public static void Update(){
        currentScene.Update();
    }

    public static void Render(){
        currentScene.Render();
    }
    
    public static void AddScene(Scene _scene){
        if(scenes.contains(_scene)){
            System.err.println("SceneManager::AddScene -> Scene already in scene list!");
            return;
        }

        scenes.add(_scene);
    }
        
    public static void LoadSceneByName(String _sceneName){
        int buildIndex = getSceneBuildIndex(_sceneName);
        if(buildIndex == -1){
            Debug.Error("No scene with name: " + _sceneName);
            return;
        }
            
        LoadScene(buildIndex);
        
    }

    /**
     * Unload current scene and load scene with given _buildIndex
     *
     * @param _buildIndex
     */
    public static void LoadScene(int _buildIndex){
        if(currentScene != null)
            currentScene.Cleanup();
        
        currentScene = scenes.get(_buildIndex);
        currentScene.Initialise();
    }
    
    /**
     * Returns the scene with the given _buildIndex.
     *
     * @param _buildIndex
     * @return Scene with _buildIndex or null
     */
    public static Scene getSceneByBuildIndex(int _buildIndex){
        Scene scene = scenes.get(_buildIndex);
        if(scene != null)
        return scene;
    
        return null;
    }
    
    /**
     * Return the scene with given _sceneName
     *
     * @param _sceneName
     * @return Scene with given _sceneName or null
     */
    public static Scene getSceneByName(String _sceneName){
        for(Scene scene : scenes){
            if(scene.getName() == _sceneName)
                return scene;
        }

        return null;
    }
    
    /**
     * Returns the buildIndex of the scene with _sceneName
     *
     * @param _sceneName The name of the scene to get the buildIndex for
     * @return A scenes buildIndex
     */
    private static int getSceneBuildIndex(String _sceneName){
        Scene scene;
        for(int i = 0; i < scenes.size(); i++){
            scene = scenes.get(i);
            if(scene.getName() == _sceneName)
                return i;
        }
    
        Debug.Error("No scene with name: " + _sceneName);
        return -1;
    }
    
    public static Scene getCurrentScene(){
        return currentScene;
    }
    
}
