package strosoft.sys.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
public class SysBasicTableField extends DataEntity implements Cloneable
{
    private  String tableName;//表名;
    private  String fieldName;//字段名;
    private  String caption;//字段标题;
    private  String sysUiControlCode;//页面控件类型编码;
    private  String formatString;//格式化字符串;
    private  Integer isPrimaryKey;//是否是主键;
    private  Integer isAutoIncrease;//是否自增;
    private  Integer required;//是否必填项;
    private  Integer isUnique;//是否唯一;
    private  Integer showInQuery;//是否查询字段;
    private  Integer showInGrid;//是否在列表中显示;
    private  Integer showInForm;//是否在表单中显示;
    private  Integer hidden;//是否隐藏;
    private  Integer enable;//是否启用;
    private  Integer columnSpan;//占用列数;
    private  Integer displayOrder;//显示顺序;
    private  String datasourceTableName;//被引用表名;
    private  String datasourceValueField;//被引用字段;
    private  String datasourceTextField;//被引用字段标题;
    private  String datasourceCondition;//被引用查询条件;
    private  String datasourceOrderBy;//被引用显示顺序;
    private  Integer formItemWidth;//表单项宽;
    private  Integer formItemHeight;//表单项高;
    private  Integer listItemWidth;//列表项宽度;
    private  String sysAlignCode;//对齐方式;
    private  String maxValue;//字段最大值;
    private  String minValue;//字段最小值;
    private  Integer maxLength;//字段最大长度;
    private  Integer minLength;//字段最小长度;
    private  String sysValidationDataTypeCode;//验证数据类型;
    private  String sysFieldDataTypeCode;//字段数据类型;
    private  Integer fieldLength;//字段长度;
    private  Integer fieldPrecision;//字段精度;
    private  Integer fieldScale;//小数点位数;
    private  String remark;//备注;
    public  void setTableName(String tableName)    
    {
        this.tableName = tableName;
    }
    public  String getTableName()    
    {
        return tableName;
    }
    public  void setFieldName(String fieldName)    
    {
        this.fieldName = fieldName;
    }
    public  String getFieldName()    
    {
        return fieldName;
    }
    public  void setCaption(String caption)    
    {
        this.caption = caption;
    }
    public  String getCaption()    
    {
        return caption;
    }
    public  void setSysUiControlCode(String sysUiControlCode)    
    {
        this.sysUiControlCode = sysUiControlCode;
    }
    public  String getSysUiControlCode()    
    {
        return sysUiControlCode;
    }
    public  void setFormatString(String formatString)    
    {
        this.formatString = formatString;
    }
    public  String getFormatString()    
    {
        return formatString;
    }
    public  void setIsPrimaryKey(Integer isPrimaryKey)    
    {
        this.isPrimaryKey = isPrimaryKey;
    }
    public  Integer getIsPrimaryKey()    
    {
        return isPrimaryKey;
    }
    public  void setIsAutoIncrease(Integer isAutoIncrease)    
    {
        this.isAutoIncrease = isAutoIncrease;
    }
    public  Integer getIsAutoIncrease()    
    {
        return isAutoIncrease;
    }
    public  void setRequired(Integer required)    
    {
        this.required = required;
    }
    public  Integer getRequired()    
    {
        return required;
    }
    public  void setIsUnique(Integer isUnique)    
    {
        this.isUnique = isUnique;
    }
    public  Integer getIsUnique()    
    {
        return isUnique;
    }
    public  void setShowInQuery(Integer showInQuery)    
    {
        this.showInQuery = showInQuery;
    }
    public  Integer getShowInQuery()    
    {
        return showInQuery;
    }
    public  void setShowInGrid(Integer showInGrid)    
    {
        this.showInGrid = showInGrid;
    }
    public  Integer getShowInGrid()    
    {
        return showInGrid;
    }
    public  void setShowInForm(Integer showInForm)    
    {
        this.showInForm = showInForm;
    }
    public  Integer getShowInForm()    
    {
        return showInForm;
    }
    public  void setHidden(Integer hidden)    
    {
        this.hidden = hidden;
    }
    public  Integer getHidden()    
    {
        return hidden;
    }
    public  void setEnable(Integer enable)    
    {
        this.enable = enable;
    }
    public  Integer getEnable()    
    {
        return enable;
    }
    public  void setColumnSpan(Integer columnSpan)    
    {
        this.columnSpan = columnSpan;
    }
    public  Integer getColumnSpan()    
    {
        return columnSpan;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setDatasourceTableName(String datasourceTableName)    
    {
        this.datasourceTableName = datasourceTableName;
    }
    public  String getDatasourceTableName()    
    {
        return datasourceTableName;
    }
    public  void setDatasourceValueField(String datasourceValueField)    
    {
        this.datasourceValueField = datasourceValueField;
    }
    public  String getDatasourceValueField()    
    {
        return datasourceValueField;
    }
    public  void setDatasourceTextField(String datasourceTextField)    
    {
        this.datasourceTextField = datasourceTextField;
    }
    public  String getDatasourceTextField()    
    {
        return datasourceTextField;
    }
    public  void setDatasourceCondition(String datasourceCondition)    
    {
        this.datasourceCondition = datasourceCondition;
    }
    public  String getDatasourceCondition()    
    {
        return datasourceCondition;
    }
    public  void setDatasourceOrderBy(String datasourceOrderBy)    
    {
        this.datasourceOrderBy = datasourceOrderBy;
    }
    public  String getDatasourceOrderBy()    
    {
        return datasourceOrderBy;
    }
    public  void setFormItemWidth(Integer formItemWidth)    
    {
        this.formItemWidth = formItemWidth;
    }
    public  Integer getFormItemWidth()    
    {
        return formItemWidth;
    }
    public  void setFormItemHeight(Integer formItemHeight)    
    {
        this.formItemHeight = formItemHeight;
    }
    public  Integer getFormItemHeight()    
    {
        return formItemHeight;
    }
    public  void setListItemWidth(Integer listItemWidth)    
    {
        this.listItemWidth = listItemWidth;
    }
    public  Integer getListItemWidth()    
    {
        return listItemWidth;
    }
    public  void setSysAlignCode(String sysAlignCode)    
    {
        this.sysAlignCode = sysAlignCode;
    }
    public  String getSysAlignCode()    
    {
        return sysAlignCode;
    }
    public  void setMaxValue(String maxValue)    
    {
        this.maxValue = maxValue;
    }
    public  String getMaxValue()    
    {
        return maxValue;
    }
    public  void setMinValue(String minValue)    
    {
        this.minValue = minValue;
    }
    public  String getMinValue()    
    {
        return minValue;
    }
    public  void setMaxLength(Integer maxLength)    
    {
        this.maxLength = maxLength;
    }
    public  Integer getMaxLength()    
    {
        return maxLength;
    }
    public  void setMinLength(Integer minLength)    
    {
        this.minLength = minLength;
    }
    public  Integer getMinLength()    
    {
        return minLength;
    }
    public  void setSysValidationDataTypeCode(String sysValidationDataTypeCode)    
    {
        this.sysValidationDataTypeCode = sysValidationDataTypeCode;
    }
    public  String getSysValidationDataTypeCode()    
    {
        return sysValidationDataTypeCode;
    }
    public  void setSysFieldDataTypeCode(String sysFieldDataTypeCode)    
    {
        this.sysFieldDataTypeCode = sysFieldDataTypeCode;
    }
    public  String getSysFieldDataTypeCode()    
    {
        return sysFieldDataTypeCode;
    }
    public  void setFieldLength(Integer fieldLength)    
    {
        this.fieldLength = fieldLength;
    }
    public  Integer getFieldLength()    
    {
        return fieldLength;
    }
    public  void setFieldPrecision(Integer fieldPrecision)    
    {
        this.fieldPrecision = fieldPrecision;
    }
    public  Integer getFieldPrecision()    
    {
        return fieldPrecision;
    }
    public  void setFieldScale(Integer fieldScale)    
    {
        this.fieldScale = fieldScale;
    }
    public  Integer getFieldScale()    
    {
        return fieldScale;
    }
    public  void setRemark(String remark)    
    {
        this.remark = remark;
    }
    public  String getRemark()    
    {
        return remark;
    }
    public  SysBasicTableField copy()    
    {
        SysBasicTableField entity = null;
        try        
        {
            entity = (SysBasicTableField) super.clone();
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