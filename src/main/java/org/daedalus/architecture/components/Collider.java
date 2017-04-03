package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;
import main.java.org.daedalus.graphics.types.MeshData;
import main.java.org.daedalus.graphics.types.RenderModel;
import main.java.org.daedalus.math.Polygon;
import main.java.org.daedalus.utils.MeshUtils;
import main.java.org.daedalus.utils.SAT;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by Stuart on 29/01/2017.
 */
public abstract class Collider extends Component{
    
    public static boolean RENDER_DEBUG_MESH = true;
    
    protected MeshData meshData;
    protected RenderModel renderModel;
    
    protected Polygon meshPolygon;
    
    public Collider(){
        super(false);
    }
    
    public boolean CollidesWith(Collider _collider){
        Vector2f[] a = new Vector2f[meshPolygon.points.length];
        for(int i = 0; i < a.length; i++){
            Vector2f t = meshPolygon.points[i];
            a[i] = new Vector2f(t.x, t.y).add(transform.position.x, transform.position.y);
        }
        Polygon polyA = Polygon.Create(a);
    
        Vector2f[] b = new Vector2f[_collider.meshPolygon.points.length];
        for(int i = 0; i < b.length; i++){
            Vector2f t = _collider.meshPolygon.points[i];
            b[i] = new Vector2f(t.x, t.y).add(_collider.transform.position.x, _collider.transform.position.y);
        }
        Polygon polyB = Polygon.Create(b);
    
        return SAT.Overlap(polyA, polyB);
    }
    
    protected abstract void UpdateMeshPolygon();
    
    protected void UpdateRenderModel(MeshData _meshData){
        if(renderModel != null)
            renderModel.ReleaseModel();
        
        meshData = _meshData;
        renderModel = RenderModel.CreateModel(_meshData.tris, _meshData.verts, null);
    }
    
}
