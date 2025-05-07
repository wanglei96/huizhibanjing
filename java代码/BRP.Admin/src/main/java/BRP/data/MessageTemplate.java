package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class MessageTemplate extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer companyId;//门店ID;
    private  String pusherCode;//推送人员（全部，部分）;
    private  Timestamp pushTime;//推送时间;
    private  String messageTypeCode;//消息类型;
    private  String content;//内容;
    private  Integer pushInterval;//推送间隔;
    private  String pushTypeCode;//推送方式;
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
    public  void setPusherCode(String pusherCode)    
    {
        this.pusherCode = pusherCode;
    }
    public  String getPusherCode()    
    {
        return pusherCode;
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
    public  void setPushInterval(Integer pushInterval)    
    {
        this.pushInterval = pushInterval;
    }
    public  Integer getPushInterval()    
    {
        return pushInterval;
    }
    public  void setPushTypeCode(String pushTypeCode)    
    {
        this.pushTypeCode = pushTypeCode;
    }
    public  String getPushTypeCode()    
    {
        return pushTypeCode;
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
    public  MessageTemplate copy()    
    {
        MessageTemplate entity = null;
        try        
        {
            entity = (MessageTemplate) super.clone();
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