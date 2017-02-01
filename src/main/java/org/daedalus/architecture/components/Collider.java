package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;
import main.java.org.daedalus.graphics.types.MeshData;
import main.java.org.daedalus.graphics.types.RenderModel;
import main.java.org.daedalus.utils.MeshUtils;

/**
 * Created by Stuart on 29/01/2017.
 */
public abstract class Collider extends Component{
    
    public static boolean RENDER_DEBUG_MESH = true;
    
    protected MeshData meshData;
    protected RenderModel renderModel;
    
    public Collider(){
        super(false);
    }
    
    public boolean Collides(Collider _collider){
        if(_collider instanceof BoxCollider)
            return vsBoxCollider((BoxCollider)_collider);
        if(_collider instanceof CircleCollider)
            return vsCircleCollider((CircleCollider)_collider);
        
        return false;
    }
    
    protected abstract boolean vsBoxCollider(BoxCollider _box);
    protected abstract boolean vsCircleCollider(CircleCollider _circle);
    
    protected void UpdateRenderModel(MeshData _meshData){
        if(renderModel != null)
            renderModel.ReleaseModel();
        
        meshData = _meshData;
        renderModel = RenderModel.CreateModel(_meshData.tris, _meshData.verts, null);
    }
    
}
