package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CompanySetting extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//商户ID;
    private  String sysSettingCode;//商户系统参数编码;
    private  String value;//值;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setSysSettingCode(String sysSettingCode)    
    {
        this.sysSettingCode = sysSettingCode;
    }
    public  String getSysSettingCode()    
    {
        return sysSettingCode;
    }
    public  void setValue(String value)    
    {
        this.value = value;
    }
    public  String getValue()    
    {
        return value;
    }
    public  CompanySetting copy()    
    {
        CompanySetting entity = null;
        try        
        {
            entity = (CompanySetting) super.clone();
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