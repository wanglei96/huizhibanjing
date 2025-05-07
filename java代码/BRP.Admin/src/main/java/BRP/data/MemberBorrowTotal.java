package BRP.data;
import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
public class MemberBorrowTotal extends DataEntity implements Cloneable
{
    public  MemberBorrowTotal copy()    
    {
        MemberBorrowTotal entity = null;
        try        
        {
            entity = (MemberBorrowTotal) super.clone();
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