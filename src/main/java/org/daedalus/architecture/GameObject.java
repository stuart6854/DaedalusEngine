package main.java.org.daedalus.architecture;

import java.util.List;

/**
 * Created by Stuart on 24/01/2017.
 */
public class GameObject {

    public String name;

    public boolean active;

    protected List<Component> components;

    public GameObject(){
        this("GameObject");
    }

    public GameObject(String _name){
        name = _name;
    }

    public <T extends Component> void AddComponent(T _comp){

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
