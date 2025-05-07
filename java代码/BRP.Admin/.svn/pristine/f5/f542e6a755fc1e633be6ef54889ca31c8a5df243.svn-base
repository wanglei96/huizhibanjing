package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class StoryVideo extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer storyVideoFileId;//视频文件ID;
    private  Integer coverImageFileId;//封面;
    private  String title;//标题;
    private  Timestamp pushDate;//推送日期;
    private  Timestamp pushTime;//推送时间;
    private  Integer playbackCount;//播放次数;
    private  Boolean isPay;//是否付费;
    private  String introduction;//简介;
    private  Boolean isTop;//是否置顶;
    private  Boolean isDeleted;//是否删除;
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
    public  void setStoryVideoFileId(Integer storyVideoFileId)    
    {
        this.storyVideoFileId = storyVideoFileId;
    }
    public  Integer getStoryVideoFileId()    
    {
        return storyVideoFileId;
    }
    public  void setCoverImageFileId(Integer coverImageFileId)    
    {
        this.coverImageFileId = coverImageFileId;
    }
    public  Integer getCoverImageFileId()    
    {
        return coverImageFileId;
    }
    public  void setTitle(String title)    
    {
        this.title = title;
    }
    public  String getTitle()    
    {
        return title;
    }
    public  void setPushDate(Timestamp pushDate)    
    {
        this.pushDate = pushDate;
    }
    public  Timestamp getPushDate()    
    {
        return pushDate;
    }
    public  void setPushTime(Timestamp pushTime)    
    {
        this.pushTime = pushTime;
    }
    public  Timestamp getPushTime()    
    {
        return pushTime;
    }
    public  void setPlaybackCount(Integer playbackCount)    
    {
        this.playbackCount = playbackCount;
    }
    public  Integer getPlaybackCount()    
    {
        return playbackCount;
    }
    public  void setIsPay(Boolean isPay)    
    {
        this.isPay = isPay;
    }
    public  Boolean getIsPay()    
    {
        return isPay;
    }
    public  void setIntroduction(String introduction)    
    {
        this.introduction = introduction;
    }
    public  String getIntroduction()    
    {
        return introduction;
    }
    public  void setIsTop(Boolean isTop)    
    {
        this.isTop = isTop;
    }
    public  Boolean getIsTop()    
    {
        return isTop;
    }
    public  void setIsDeleted(Boolean isDeleted)    
    {
        this.isDeleted = isDeleted;
    }
    public  Boolean getIsDeleted()    
    {
        return isDeleted;
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
    public  StoryVideo copy()    
    {
        StoryVideo entity = null;
        try        
        {
            entity = (StoryVideo) super.clone();
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