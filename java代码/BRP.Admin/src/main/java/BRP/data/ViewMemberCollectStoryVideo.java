package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewMemberCollectStoryVideo extends DataEntity implements Cloneable
{
    private  Integer id;//ID;
    private  Integer memberId;//会员ID;
    private  Integer storyVideoId;//晚安故事ID;
    private  Timestamp createTime;//创建时间;
    private  String storyVideoTitle;//标题;
    private  Timestamp storyVideoCreateTime;//创建时间;
    private  String coverImageFilePath;
    private  String videoImageFilePath;
    private  String storyVideoFilePath;
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
    public  void setStoryVideoId(Integer storyVideoId)    
    {
        this.storyVideoId = storyVideoId;
    }
    public  Integer getStoryVideoId()    
    {
        return storyVideoId;
    }
    public  void setCreateTime(Timestamp createTime)    
    {
        this.createTime = createTime;
    }
    public  Timestamp getCreateTime()    
    {
        return createTime;
    }
    public  void setStoryVideoTitle(String storyVideoTitle)    
    {
        this.storyVideoTitle = storyVideoTitle;
    }
    public  String getStoryVideoTitle()    
    {
        return storyVideoTitle;
    }
    public  void setStoryVideoCreateTime(Timestamp storyVideoCreateTime)    
    {
        this.storyVideoCreateTime = storyVideoCreateTime;
    }
    public  Timestamp getStoryVideoCreateTime()    
    {
        return storyVideoCreateTime;
    }
    public  void setCoverImageFilePath(String coverImageFilePath)    
    {
        this.coverImageFilePath = coverImageFilePath;
    }
    public  String getCoverImageFilePath()    
    {
        return coverImageFilePath;
    }
    public  void setVideoImageFilePath(String videoImageFilePath)    
    {
        this.videoImageFilePath = videoImageFilePath;
    }
    public  String getVideoImageFilePath()    
    {
        return videoImageFilePath;
    }
    public  void setStoryVideoFilePath(String storyVideoFilePath)    
    {
        this.storyVideoFilePath = storyVideoFilePath;
    }
    public  String getStoryVideoFilePath()    
    {
        return storyVideoFilePath;
    }
    public  ViewMemberCollectStoryVideo copy()    
    {
        ViewMemberCollectStoryVideo entity = null;
        try        
        {
            entity = (ViewMemberCollectStoryVideo) super.clone();
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