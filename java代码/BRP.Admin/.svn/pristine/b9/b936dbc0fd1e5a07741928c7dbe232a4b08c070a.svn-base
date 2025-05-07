package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import strosoft.app.data.DataEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class CompanySettlement extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//商户ID;
    private  BigDecimal settlementAmount;//已结算金额;
    private  Timestamp settlementDate;//结算日期;
    private  BigDecimal serviceAmount;//服务费;
    private  Timestamp startDate;//开始日期;
    private  Timestamp endDate;//结束日期;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//修改时间;
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
    public  void setSettlementAmount(BigDecimal settlementAmount)    
    {
        this.settlementAmount = settlementAmount;
    }
    public  BigDecimal getSettlementAmount()    
    {
        return settlementAmount;
    }
    public  void setSettlementDate(Timestamp settlementDate)    
    {
        this.settlementDate = settlementDate;
    }
    public  Timestamp getSettlementDate()    
    {
        return settlementDate;
    }
    public  void setServiceAmount(BigDecimal serviceAmount)    
    {
        this.serviceAmount = serviceAmount;
    }
    public  BigDecimal getServiceAmount()    
    {
        return serviceAmount;
    }
    public  void setStartDate(Timestamp startDate)    
    {
        this.startDate = startDate;
    }
    public  Timestamp getStartDate()    
    {
        return startDate;
    }
    public  void setEndDate(Timestamp endDate)    
    {
        this.endDate = endDate;
    }
    public  Timestamp getEndDate()    
    {
        return endDate;
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
    public  CompanySettlement copy()    
    {
        CompanySettlement entity = null;
        try        
        {
            entity = (CompanySettlement) super.clone();
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