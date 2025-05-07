package BRP.data;
import net.sf.json.JSONObject;
import java.lang.Integer;
import java.math.BigInteger;
import java.lang.Boolean;
import strosoft.app.util.JsonHelper;
import java.lang.String;
import strosoft.app.data.DataEntity;
import java.sql.Timestamp;
public class ViewStoryVideo extends DataEntity implements Cloneable
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
    private  String storyVideoFilePath;//视频文件路径;
    private  String storyVideoFileName;//视频文件名称;
    private  String videoImageFilePath;//视频图片文件路径;
    private  String coverImageFilePath;//图片文件路径;
    private  String coverImageFileName;//图片文件路径;
    private  BigInteger totalBrowsedCount;//观看人数;
    private  BigInteger totalCollectCount;//点赞人数;
    private  BigInteger totalLikeCount;//收藏人数;
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
    public  void setStoryVideoFilePath(String storyVideoFilePath)    
    {
        this.storyVideoFilePath = storyVideoFilePath;
    }
    public  String getStoryVideoFilePath()    
    {
        return storyVideoFilePath;
    }
    public  void setStoryVideoFileName(String storyVideoFileName)    
    {
        this.storyVideoFileName = storyVideoFileName;
    }
    public  String getStoryVideoFileName()    
    {
        return storyVideoFileName;
    }
    public  void setVideoImageFilePath(String videoImageFilePath)    
    {
        this.videoImageFilePath = videoImageFilePath;
    }
    public  String getVideoImageFilePath()    
    {
        return videoImageFilePath;
    }
    public  void setCoverImageFilePath(String coverImageFilePath)    
    {
        this.coverImageFilePath = coverImageFilePath;
    }
    public  String getCoverImageFilePath()    
    {
        return coverImageFilePath;
    }
    public  void setCoverImageFileName(String coverImageFileName)    
    {
        this.coverImageFileName = coverImageFileName;
    }
    public  String getCoverImageFileName()    
    {
        return coverImageFileName;
    }
    public  void setTotalBrowsedCount(BigInteger totalBrowsedCount)    
    {
        this.totalBrowsedCount = totalBrowsedCount;
    }
    public  BigInteger getTotalBrowsedCount()    
    {
        return totalBrowsedCount;
    }
    public  void setTotalCollectCount(BigInteger totalCollectCount)    
    {
        this.totalCollectCount = totalCollectCount;
    }
    public  BigInteger getTotalCollectCount()    
    {
        return totalCollectCount;
    }
    public  void setTotalLikeCount(BigInteger totalLikeCount)    
    {
        this.totalLikeCount = totalLikeCount;
    }
    public  BigInteger getTotalLikeCount()    
    {
        return totalLikeCount;
    }
    public  ViewStoryVideo copy()    
    {
        ViewStoryVideo entity = null;
        try        
        {
            entity = (ViewStoryVideo) super.clone();
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