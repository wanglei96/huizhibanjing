package BRP.data;
import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
public class ViewMemberCardChangeLog extends DataEntity implements Cloneable
{
    public  ViewMemberCardChangeLog copy()    
    {
        ViewMemberCardChangeLog entity = null;
        try        
        {
            entity = (ViewMemberCardChangeLog) super.clone();
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