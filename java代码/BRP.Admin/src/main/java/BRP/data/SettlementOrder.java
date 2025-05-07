package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
public class SettlementOrder extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  Timestamp businessDate;//结算时间;
    private  Timestamp beginDate;//结算开始日期;
    private  Timestamp endDate;//结算结束日期;
    private  BigDecimal ratio;//扣点比率;
    private  BigDecimal amount;//总金额;
    private  BigDecimal platformAmount;//平台金额;
    private  BigDecimal companyAmount;//门店金额;
    private  String settlementOrderStatusCode;//结算单状态编码;
    private  String retailOrderIds;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
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
    public  void setBusinessDate(Timestamp businessDate)    
    {
        this.businessDate = businessDate;
    }
    public  Timestamp getBusinessDate()    
    {
        return businessDate;
    }
    public  void setBeginDate(Timestamp beginDate)    
    {
        this.beginDate = beginDate;
    }
    public  Timestamp getBeginDate()    
    {
        return beginDate;
    }
    public  void setEndDate(Timestamp endDate)    
    {
        this.endDate = endDate;
    }
    public  Timestamp getEndDate()    
    {
        return endDate;
    }
    public  void setRatio(BigDecimal ratio)    
    {
        this.ratio = ratio;
    }
    public  BigDecimal getRatio()    
    {
        return ratio;
    }
    public  void setAmount(BigDecimal amount)    
    {
        this.amount = amount;
    }
    public  BigDecimal getAmount()    
    {
        return amount;
    }
    public  void setPlatformAmount(BigDecimal platformAmount)    
    {
        this.platformAmount = platformAmount;
    }
    public  BigDecimal getPlatformAmount()    
    {
        return platformAmount;
    }
    public  void setCompanyAmount(BigDecimal companyAmount)    
    {
        this.companyAmount = companyAmount;
    }
    public  BigDecimal getCompanyAmount()    
    {
        return companyAmount;
    }
    public  void setSettlementOrderStatusCode(String settlementOrderStatusCode)    
    {
        this.settlementOrderStatusCode = settlementOrderStatusCode;
    }
    public  String getSettlementOrderStatusCode()    
    {
        return settlementOrderStatusCode;
    }
    public  void setRetailOrderIds(String retailOrderIds)    
    {
        this.retailOrderIds = retailOrderIds;
    }
    public  String getRetailOrderIds()    
    {
        return retailOrderIds;
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
    public  SettlementOrder copy()    
    {
        SettlementOrder entity = null;
        try        
        {
            entity = (SettlementOrder) super.clone();
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