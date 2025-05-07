package BRP.data;
import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
public class ViewCylinderOrder extends DataEntity implements Cloneable
{
    public  ViewCylinderOrder copy()    
    {
        ViewCylinderOrder entity = null;
        try        
        {
            entity = (ViewCylinderOrder) super.clone();
        }
         catch (CloneNotSupportedException e)         
        {
            e.printStackTrace();
        }
        return entity;
    }
    public  JSONObject toJson()    
    {
        return JSONObject.fromObject(this,JsonHelper.getJsonConfig());
    }
}