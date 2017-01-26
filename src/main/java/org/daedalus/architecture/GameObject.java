package main.java.org.daedalus.architecture;

import main.java.org.daedalus.architecture.components.Transform;
import main.java.org.daedalus.utils.Debug;

import java.util.List;

/**
 * Created by Stuart on 24/01/2017.
 */
public class GameObject {

    public String name;

    public boolean active;

    protected List<Component> components;
    
    private Transform transform;

    public GameObject(){
        this("GameObject");
    }

    public GameObject(String _name){
        name = _name;
        
        transform = AddComponent(new Transform());
    }

    public <T extends Component> T AddComponent(Component _comp){
        if(_comp.getSingleInstance()) {
            if (CountCompOfType(_comp.getClass()) >= 0) {
                Debug.Error("Can only have ONE instance of component " + _comp.getClass().getSimpleName() + " on GameObject " + name);
                return null;
            }
        }
        
        components.add(_comp);
        return (T)_comp;
    }

    public <T extends Component> Component GetComponent(Class<T> _type){
        for(Component comp : components){
            if(comp.getClass() == _type)
                return comp;
        }

        return null;
    }

    private <T extends Component> int CountCompOfType(Class<T> _type) {
        int i = 0;
        for (Component comp : components){
            if(comp.getClass() == _type)
                i++;
        }

        return i;
    }

}
