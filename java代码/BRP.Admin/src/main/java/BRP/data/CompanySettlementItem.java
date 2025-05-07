package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class CompanySettlementItem extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companySettlementId;//商户结算ID;
    private  Integer salesOrderId;//订单ID;
    private  Timestamp createTime;//创建时间;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setCompanySettlementId(Integer companySettlementId)    
    {
        this.companySettlementId = companySettlementId;
    }
    public  Integer getCompanySettlementId()    
    {
        return companySettlementId;
    }
    public  void setSalesOrderId(Integer salesOrderId)    
    {
        this.salesOrderId = salesOrderId;
    }
    public  Integer getSalesOrderId()    
    {
        return salesOrderId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  CompanySettlementItem copy()    
    {
        CompanySettlementItem entity = null;
        try        
        {
            entity = (CompanySettlementItem) super.clone();
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