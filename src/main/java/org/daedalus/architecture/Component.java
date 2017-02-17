package main.java.org.daedalus.architecture;

import main.java.org.daedalus.architecture.components.Transform;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Component {

    private boolean singleInstance; // Are GameObjects allowed to have multiple of this component?
    
    protected GameObject gameObject; // The GameObject the component is attached to.
    protected Transform transform;
    
    public Component(boolean _singleInstance){
        singleInstance = _singleInstance;
    }
    
    public void Update(){  }
    
    public void FixedUpdate(){  } // Physics Updates
    
    public void Render(){  }
    
    public boolean getSingleInstance(){
        return singleInstance;
    }
    
    public void SetGameObject(GameObject _gameObject){
        gameObject = _gameObject;
        transform = gameObject.transform;
    }
    
}
