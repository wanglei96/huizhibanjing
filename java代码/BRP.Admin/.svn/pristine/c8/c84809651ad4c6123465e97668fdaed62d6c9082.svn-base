package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class Message extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  Timestamp pushTime;//推送时间;
    private  String messageTypeCode;//消息类型;
    private  String content;//内容;
    private  Integer companyId;//商户ID;
    private  String msg;
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
    public  void setMemberId(Integer memberId)    
    {
        this.memberId = memberId;
    }
    public  Integer getMemberId()    
    {
        return memberId;
    }
    public  void setPushTime(Timestamp pushTime)    
    {
        this.pushTime = pushTime;
    }
    public  Timestamp getPushTime()    
    {
        return pushTime;
    }
    public  void setMessageTypeCode(String messageTypeCode)    
    {
        this.messageTypeCode = messageTypeCode;
    }
    public  String getMessageTypeCode()    
    {
        return messageTypeCode;
    }
    public  void setContent(String content)    
    {
        this.content = content;
    }
    public  String getContent()    
    {
        return content;
    }
    public  void setCompanyId(Integer companyId)    
    {
        this.companyId = companyId;
    }
    public  Integer getCompanyId()    
    {
        return companyId;
    }
    public  void setMsg(String msg)    
    {
        this.msg = msg;
    }
    public  String getMsg()    
    {
        return msg;
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
    public  Message copy()    
    {
        Message entity = null;
        try        
        {
            entity = (Message) super.clone();
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