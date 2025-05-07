package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewMemberBookSubscription extends DataEntity implements Cloneable
{
    private  Integer id;// ID;
    private  Integer memberId;//会员ID;
    private  Integer bookSkuId;//图书SKUID;
    private  Timestamp createTime;//创建时间;
    private  Integer companyId;//门店ID;
    private  String memberName;//名称;
    private  String wxOpenId;//公众号OpenID;
    private  String wxaOpenId;//微信OpenID;
    private  String bookName;//书名;
    public  void setId(Integer id)    
    {
        this.id = id;
    }
    public  Integer getId()    
    {
        return id;
    }
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setBookSkuId(Integer bookSkuId)    
    {
        this.bookSkuId = bookSkuId;
    }
    public  Integer getBookSkuId()    
    {
        return bookSkuId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setMemberName(String memberName)    
    {
        this.memberName = memberName;
    }
    public  String getMemberName()    
    {
        return memberName;
    }
    public  void setWxOpenId(String wxOpenId)    
    {
        this.wxOpenId = wxOpenId;
    }
    public  String getWxOpenId()    
    {
        return wxOpenId;
    }
    public  void setWxaOpenId(String wxaOpenId)    
    {
        this.wxaOpenId = wxaOpenId;
    }
    public  String getWxaOpenId()    
    {
        return wxaOpenId;
    }
    public  void setBookName(String bookName)    
    {
        this.bookName = bookName;
    }
    public  String getBookName()    
    {
        return bookName;
    }
    public  ViewMemberBookSubscription copy()    
    {
        ViewMemberBookSubscription entity = null;
        try        
        {
            entity = (ViewMemberBookSubscription) super.clone();
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