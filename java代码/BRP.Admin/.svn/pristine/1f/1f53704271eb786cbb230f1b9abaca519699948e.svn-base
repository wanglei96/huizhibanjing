package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewCompanyMenu extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String name;//名称;
    private  Integer parentId;//上级ID;
    private  String value;//值;
    private  String url;//导航地址;
    private  Integer displayOrder;//排序;
    private  String sysMenuTypeName;//菜单类型名称;
    private  String sysMenuTypeCode;//菜单类型编码;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setParentId(Integer parentId)    
    {
        this.parentId = parentId;
    }
    public  Integer getParentId()    
    {
        return parentId;
    }
    public  void setValue(String value)    
    {
        this.value = value;
    }
    public  String getValue()    
    {
        return value;
    }
    public  void setUrl(String url)    
    {
        this.url = url;
    }
    public  String getUrl()    
    {
        return url;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setSysMenuTypeName(String sysMenuTypeName)    
    {
        this.sysMenuTypeName = sysMenuTypeName;
    }
    public  String getSysMenuTypeName()    
    {
        return sysMenuTypeName;
    }
    public  void setSysMenuTypeCode(String sysMenuTypeCode)    
    {
        this.sysMenuTypeCode = sysMenuTypeCode;
    }
    public  String getSysMenuTypeCode()    
    {
        return sysMenuTypeCode;
    }
    public  ViewCompanyMenu copy()    
    {
        ViewCompanyMenu entity = null;
        try        
        {
            entity = (ViewCompanyMenu) super.clone();
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