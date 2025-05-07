package strosoft.cms.model;
import strosoft.cms.data.CmsChannelGroupMaster;
public class CmsChannelGroupManager extends CmsChannelGroupMaster 
{
    private static CmsChannelGroupManager _instance;
    private CmsChannelGroupManager()    
    {
        this.setTableName("cms_channel_group");
        this.setTypeName("CmsChannelGroup");
    }
    public  static CmsChannelGroupManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsChannelGroupManager();
        }
        return _instance;
    }
}