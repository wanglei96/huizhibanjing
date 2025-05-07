package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewCompanyChannel extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  String code;//编码;
    private  String name;//标题;
    private  Integer imageFileId;//栏目图片文件ID;
    private  String content;//栏目内容;
    private  String companyChannelUrl;//导航地址;
    private  Integer parentId;//上级ID;
    private  Integer displayOrder;//显示顺序;
    private  Integer companyId;//商户ID;
    private  Integer companyUserId;//商户用户ID;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//修改时间;
    private  Integer sysUploadFilelId;//文件ID;
    private  String sysUploadFileName;//文件名称;
    private  String sysUploadFilePath;//文件路径;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setCode(String code)    
    {
        this.code = code;
    }
    public  String getCode()    
    {
        return code;
    }
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setImageFileId(Integer imageFileId)    
    {
        this.imageFileId = imageFileId;
    }
    public  Integer getImageFileId()    
    {
        return imageFileId;
    }
    public  void setContent(String content)    
    {
        this.content = content;
    }
    public  String getContent()    
    {
        return content;
    }
    public  void setCompanyChannelUrl(String companyChannelUrl)    
    {
        this.companyChannelUrl = companyChannelUrl;
    }
    public  String getCompanyChannelUrl()    
    {
        return companyChannelUrl;
    }
    public  void setParentId(Integer parentId)    
    {
        this.parentId = parentId;
    }
    public  Integer getParentId()    
    {
        return parentId;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setUpdateTime(Timestamp updateTime)    
    {
        this.updateTime = updateTime;
    }
    public  Timestamp getUpdateTime()    
    {
        return updateTime;
    }
    public  void setSysUploadFilelId(Integer sysUploadFilelId)    
    {
        this.sysUploadFilelId = sysUploadFilelId;
    }
    public  Integer getSysUploadFilelId()    
    {
        return sysUploadFilelId;
    }
    public  void setSysUploadFileName(String sysUploadFileName)    
    {
        this.sysUploadFileName = sysUploadFileName;
    }
    public  String getSysUploadFileName()    
    {
        return sysUploadFileName;
    }
    public  void setSysUploadFilePath(String sysUploadFilePath)    
    {
        this.sysUploadFilePath = sysUploadFilePath;
    }
    public  String getSysUploadFilePath()    
    {
        return sysUploadFilePath;
    }
    public  ViewCompanyChannel copy()    
    {
        ViewCompanyChannel entity = null;
        try        
        {
            entity = (ViewCompanyChannel) super.clone();
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