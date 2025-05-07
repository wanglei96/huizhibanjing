package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class ViewCompanySetting extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//商户ID;
    private  String sysSettingCode;//商户系统参数编码;
    private  String value;//值;
    private  String name;//参数名称;
    private  String sysDataTypeCode;//数据类型;
    private  String sysSettingCategoryCode;//参数分类;
    private  String sysUiControlCode;//控件类型;
    private  Integer required;//是否必填;
    private  Integer readonly;//是否只读;
    private  String remark;//备注;
    private  Integer displayOrder;//排序;
    private  String formatString;//格式化字符串;
    private  Integer hidden;//是否隐藏;
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
    public  void setName(String name)    
    {
        this.name = name;
    }
    public  String getName()    
    {
        return name;
    }
    public  void setSysDataTypeCode(String sysDataTypeCode)    
    {
        this.sysDataTypeCode = sysDataTypeCode;
    }
    public  String getSysDataTypeCode()    
    {
        return sysDataTypeCode;
    }
    public  void setSysSettingCategoryCode(String sysSettingCategoryCode)    
    {
        this.sysSettingCategoryCode = sysSettingCategoryCode;
    }
    public  String getSysSettingCategoryCode()    
    {
        return sysSettingCategoryCode;
    }
    public  void setSysUiControlCode(String sysUiControlCode)    
    {
        this.sysUiControlCode = sysUiControlCode;
    }
    public  String getSysUiControlCode()    
    {
        return sysUiControlCode;
    }
    public  void setRequired(Integer required)    
    {
        this.required = required;
    }
    public  Integer getRequired()    
    {
        return required;
    }
    public  void setReadonly(Integer readonly)    
    {
        this.readonly = readonly;
    }
    public  Integer getReadonly()    
    {
        return readonly;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setFormatString(String formatString)    
    {
        this.formatString = formatString;
    }
    public  String getFormatString()    
    {
        return formatString;
    }
    public  void setHidden(Integer hidden)    
    {
        this.hidden = hidden;
    }
    public  Integer getHidden()    
    {
        return hidden;
    }
    public  ViewCompanySetting copy()    
    {
        ViewCompanySetting entity = null;
        try        
        {
            entity = (ViewCompanySetting) super.clone();
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