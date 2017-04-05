package main.java.org.daedalus.architecture.components;

import main.java.org.daedalus.architecture.Component;
import main.java.org.daedalus.graphics.types.MeshData;
import main.java.org.daedalus.graphics.types.RenderModel;
import main.java.org.daedalus.math.Polygon;
import main.java.org.daedalus.utils.MeshUtils;
import main.java.org.daedalus.utils.SAT;
import org.joml.Matrix4f;
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
        Matrix4f transformMat = transform.getTransformMatrix();
        Vector2f[] a = new Vector2f[meshPolygon.points.length];
        for(int i = 0; i < a.length; i++){
            Vector2f t2 = meshPolygon.points[i];
            Vector3f t3 = new Vector3f(t2.x, t2.y, 0);
            t3.mulPosition(transformMat);
            a[i] = new Vector2f(t3.x, t3.y);
        }
        Polygon polyA = Polygon.Create(a);
    
        transformMat = _collider.transform.getTransformMatrix();
        Vector2f[] b = new Vector2f[_collider.meshPolygon.points.length];
        for(int i = 0; i < b.length; i++){
            Vector2f t2 = _collider.meshPolygon.points[i];
            Vector3f t3 = new Vector3f(t2.x, t2.y, 0);
            t3.mulPosition(transformMat);
            b[i] = new Vector2f(t3.x, t3.y);
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
