package main.java.org.daedalus.architecture;

/**
 * Created by Stuart on 25/01/2017.
 */
public class Component {

    private boolean singleInstance; // Are GameObjects allowed to have multiple of this component?
    
    public Component(boolean _singleInstance){
        singleInstance = _singleInstance;
    }
    
    public boolean getSingleInstance(){
        return singleInstance;
    }
    
}
