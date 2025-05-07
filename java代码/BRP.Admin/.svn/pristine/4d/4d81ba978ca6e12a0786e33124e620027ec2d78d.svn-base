package strosoft.cms.model;
import strosoft.cms.data.CmsContentGroupMaster;
public class CmsContentGroupManager extends CmsContentGroupMaster 
{
    private static CmsContentGroupManager _instance;
    private CmsContentGroupManager()    
    {
        this.setTableName("cms_content_group");
        this.setTypeName("CmsContentGroup");
    }
    public  static CmsContentGroupManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsContentGroupManager();
        }
        return _instance;
    }
}