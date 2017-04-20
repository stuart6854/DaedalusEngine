package main.java.org.daedalus.architecture;

import main.java.org.daedalus.architecture.components.RectTransform;
import main.java.org.daedalus.architecture.components.Transform;

/**
 * Created by Stuart on 11/04/2017.
 */
public class UIObject extends GameObject {

    public UIObject(){
        super();
        
        RemoveComponent(Transform.class);
        transform = AddComponent(new RectTransform());
    }

}
