package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewWaybillItem extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer bookOrderId;//借书单ID;
    private  Integer bookId;//图书ID;
    private  Boolean isDamage;//是否损坏;
    private  String bookOrderTypeCode;//书单类型编码;
    private  String deliverStatusCode;//配送状态编码;
    private  String deliverStatusName;//配送状态名称;
    private  Integer delivererId;
    private  Integer displayOrder;
    private  String bookOrderTypeName;//书单类型名称;
    private  Timestamp createTime;//创建时间;
    private  Timestamp updateTime;//更新时间;
    private  String bookName;//书名;
    private  String bookNo;//ISBN;
    private  String bookcaseName;//书柜名称;
    private  String bindingTypeName;//装订方式;
    private  Integer bookcaseId;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setBookOrderId(Integer bookOrderId)    
    {
        this.bookOrderId = bookOrderId;
    }
    public  Integer getBookOrderId()    
    {
        return bookOrderId;
    }
    public  void setBookId(Integer bookId)    
    {
        this.bookId = bookId;
    }
    public  Integer getBookId()    
    {
        return bookId;
    }
    public  void setIsDamage(Boolean isDamage)    
    {
        this.isDamage = isDamage;
    }
    public  Boolean getIsDamage()    
    {
        return isDamage;
    }
    public  void setBookOrderTypeCode(String bookOrderTypeCode)    
    {
        this.bookOrderTypeCode = bookOrderTypeCode;
    }
    public  String getBookOrderTypeCode()    
    {
        return bookOrderTypeCode;
    }
    public  void setDeliverStatusCode(String deliverStatusCode)    
    {
        this.deliverStatusCode = deliverStatusCode;
    }
    public  String getDeliverStatusCode()    
    {
        return deliverStatusCode;
    }
    public  void setDeliverStatusName(String deliverStatusName)    
    {
        this.deliverStatusName = deliverStatusName;
    }
    public  String getDeliverStatusName()    
    {
        return deliverStatusName;
    }
    public  void setDelivererId(Integer delivererId)    
    {
        this.delivererId = delivererId;
    }
    public  Integer getDelivererId()    
    {
        return delivererId;
    }
    public  void setDisplayOrder(Integer displayOrder)    
    {
        this.displayOrder = displayOrder;
    }
    public  Integer getDisplayOrder()    
    {
        return displayOrder;
    }
    public  void setBookOrderTypeName(String bookOrderTypeName)    
    {
        this.bookOrderTypeName = bookOrderTypeName;
    }
    public  String getBookOrderTypeName()    
    {
        return bookOrderTypeName;
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
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
    }
    public  void setBookNo(String bookNo)    
    {
        this.bookNo = bookNo;
    }
    public  String getBookNo()    
    {
        return bookNo;
    }
    public  void setBookcaseName(String bookcaseName)    
    {
        this.bookcaseName = bookcaseName;
    }
    public  String getBookcaseName()    
    {
        return bookcaseName;
    }
    public  void setBindingTypeName(String bindingTypeName)    
    {
        this.bindingTypeName = bindingTypeName;
    }
    public  String getBindingTypeName()    
    {
        return bindingTypeName;
    }
    public  void setBookcaseId(Integer bookcaseId)    
    {
        this.bookcaseId = bookcaseId;
    }
    public  Integer getBookcaseId()    
    {
        return bookcaseId;
    }
    public  ViewWaybillItem copy()    
    {
        ViewWaybillItem entity = null;
        try        
        {
            entity = (ViewWaybillItem) super.clone();
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