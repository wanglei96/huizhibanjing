package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewSysMenu extends DataEntity implements Cloneable
{
    private  Integer id;//;
    private  String name;//;
    private  String value;//;
    private  Integer parentId;//;
    private  String url;//;
    private  String path;//路径;
    private  String icon;//图片;
    private  Integer displayOrder;//;
    private  String sysMenuTypeCode;//;
    private  Boolean showInDashboard;//;
    private  Integer pictureId;//菜单图片;
    private  String picturePath;//;
    private  Integer activationPictureId;//菜单被选中时图片;
    private  String activationPicturePath;//;
    private  String sysMenuTypeName;//;
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
    public  void setValue(String value)    
    {
        this.value = value;
    }
    public  String getValue()    
    {
        return value;
    }
    public  void setParentId(Integer parentId)    
    {
        this.parentId = parentId;
    }
    public  Integer getParentId()    
    {
        return parentId;
    }
    public  void setUrl(String url)    
    {
        this.url = url;
    }
    public  String getUrl()    
    {
        return url;
    }
    public  void setPath(String path)    
    {
        this.path = path;
    }
    public  String getPath()    
    {
        return path;
    }
    public  void setIcon(String icon)    
    {
        this.icon = icon;
    }
    public  String getIcon()    
    {
        return icon;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setSysMenuTypeCode(String sysMenuTypeCode)    
    {
        this.sysMenuTypeCode = sysMenuTypeCode;
    }
    public  String getSysMenuTypeCode()    
    {
        return sysMenuTypeCode;
    }
    public  void setShowInDashboard(Boolean showInDashboard)    
    {
        this.showInDashboard = showInDashboard;
    }
    public  Boolean getShowInDashboard()    
    {
        return showInDashboard;
    }
    public  void setPictureId(Integer pictureId)    
    {
        this.pictureId = pictureId;
    }
    public  Integer getPictureId()    
    {
        return pictureId;
    }
    public  void setPicturePath(String picturePath)    
    {
        this.picturePath = picturePath;
    }
    public  String getPicturePath()    
    {
        return picturePath;
    }
    public  void setActivationPictureId(Integer activationPictureId)    
    {
        this.activationPictureId = activationPictureId;
    }
    public  Integer getActivationPictureId()    
    {
        return activationPictureId;
    }
    public  void setActivationPicturePath(String activationPicturePath)    
    {
        this.activationPicturePath = activationPicturePath;
    }
    public  String getActivationPicturePath()    
    {
        return activationPicturePath;
    }
    public  void setSysMenuTypeName(String sysMenuTypeName)    
    {
        this.sysMenuTypeName = sysMenuTypeName;
    }
    public  String getSysMenuTypeName()    
    {
        return sysMenuTypeName;
    }
    public  ViewSysMenu copy()    
    {
        ViewSysMenu entity = null;
        try        
        {
            entity = (ViewSysMenu) super.clone();
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