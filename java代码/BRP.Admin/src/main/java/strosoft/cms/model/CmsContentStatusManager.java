package strosoft.cms.model;
import strosoft.cms.data.CmsContentStatusMaster;
public class CmsContentStatusManager extends CmsContentStatusMaster 
{
    private static CmsContentStatusManager _instance;
    private CmsContentStatusManager()    
    {
        this.setTableName("cms_content_status");
        this.setTypeName("CmsContentStatus");
    }
    public  static CmsContentStatusManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsContentStatusManager();
        }
        return _instance;
    }
}