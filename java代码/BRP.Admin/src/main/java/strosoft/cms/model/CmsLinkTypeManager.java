package strosoft.cms.model;
import strosoft.cms.data.CmsLinkTypeMaster;
public class CmsLinkTypeManager extends CmsLinkTypeMaster 
{
    private static CmsLinkTypeManager _instance;
    private CmsLinkTypeManager()    
    {
        this.setTableName("cms_link_type");
        this.setTypeName("CmsLinkType");
    }
    public  static CmsLinkTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsLinkTypeManager();
        }
        return _instance;
    }
}