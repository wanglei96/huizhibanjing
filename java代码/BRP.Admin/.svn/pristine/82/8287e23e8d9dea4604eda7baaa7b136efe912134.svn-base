package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewBookSkuRevise extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookSkuId;//图书SKUID;
    private  Integer companyUserId;//商户用户ID;
    private  Integer approverId;//处理人ID;
    private  Timestamp createTime;//创建时间;
    private  String bookName;//书名;
    private  Integer companyId;//服务商id;
    private  String companyUserName;//姓名;
    private  String approverName;//名称;
    private  String companyNo;//门店编号;
    private  String companyName;//门店名称;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setCompanyUserId(Integer companyUserId)    
    {
        this.companyUserId = companyUserId;
    }
    public  Integer getCompanyUserId()    
    {
        return companyUserId;
    }
    public  void setApproverId(Integer approverId)    
    {
        this.approverId = approverId;
    }
    public  Integer getApproverId()    
    {
        return approverId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setCompanyUserName(String companyUserName)    
    {
        this.companyUserName = companyUserName;
    }
    public  String getCompanyUserName()    
    {
        return companyUserName;
    }
    public  void setApproverName(String approverName)    
    {
        this.approverName = approverName;
    }
    public  String getApproverName()    
    {
        return approverName;
    }
    public  void setCompanyNo(String companyNo)    
    {
        this.companyNo = companyNo;
    }
    public  String getCompanyNo()    
    {
        return companyNo;
    }
    public  void setCompanyName(String companyName)    
    {
        this.companyName = companyName;
    }
    public  String getCompanyName()    
    {
        return companyName;
    }
    public  ViewBookSkuRevise copy()    
    {
        ViewBookSkuRevise entity = null;
        try        
        {
            entity = (ViewBookSkuRevise) super.clone();
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