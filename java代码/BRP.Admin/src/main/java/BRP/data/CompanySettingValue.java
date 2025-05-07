package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class CompanySettingValue extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//商户ID;
    private  String companySettingCode;//商户系统参数编码;
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
    public  void setCompanySettingCode(String companySettingCode)    
    {
        this.companySettingCode = companySettingCode;
    }
    public  String getCompanySettingCode()    
    {
        return companySettingCode;
    }
    public  void setValue(String value)    
    {
        this.value = value;
    }
    public  String getValue()    
    {
        return value;
    }
    public  CompanySettingValue copy()    
    {
        CompanySettingValue entity = null;
        try        
        {
            entity = (CompanySettingValue) super.clone();
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