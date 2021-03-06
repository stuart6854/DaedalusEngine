package main.java.org.daedalus.architecture;

import main.java.org.daedalus.architecture.components.Transform;
import main.java.org.daedalus.utils.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stuart on 24/01/2017.
 */
public class GameObject {

    public String name;

    public boolean active;

    protected List<Component> components;
    
    protected Transform transform;
    
    public GameObject(){
        this("GameObject");
    }

    public GameObject(String _name){
        components = new ArrayList<>(); //NOTE: Important we initialise arrays/lists before anything else.
        
        name = _name;
        transform = AddComponent(new Transform());
    }
    
    public void Update(){
        for(Component component : components)
            component.Update();
    }
    
    public void Render(){
        for(Component component : components)
            component.Render();
    }
    
    public <T extends Component> T AddComponent(Component _comp){
        if(_comp.getSingleInstance()) {
            if (CountCompOfType(_comp.getClass()) > 0) {
                Debug.Error("Can only have ONE instance of component " + _comp.getClass().getSimpleName() + " on GameObject " + name);
                return null;
            }
        }
        
        _comp.SetGameObject(this);
        components.add(_comp);
        return (T)_comp;
    }
    
    public void RemoveComponent(Class _type){
        String typeName = _type.getSimpleName();
        for(Component comp : components){
            String className = comp.getClass().getSimpleName();
            String superClassName = comp.getClass().getSuperclass().getSimpleName();
            if(className.equals(typeName) || superClassName.equals(typeName)) {
                components.remove(comp);
                return;
            }
        }
    }

    public <T extends Component> Component GetComponent(Class<T> _type){
        String typeName = _type.getSimpleName();
        for(Component comp : components){
            String className = comp.getClass().getSimpleName();
            String superClassName = comp.getClass().getSuperclass().getSimpleName();
            if(className.equals(typeName) || superClassName.equals(typeName))
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
    
    public Transform getTransform(){
        return transform;
    }
    
}
