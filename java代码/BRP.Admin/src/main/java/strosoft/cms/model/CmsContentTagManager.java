package strosoft.cms.model;
import strosoft.cms.data.CmsContentTagMaster;
public class CmsContentTagManager extends CmsContentTagMaster 
{
    private static CmsContentTagManager _instance;
    private CmsContentTagManager()    
    {
        this.setTableName("cms_content_tag");
        this.setTypeName("CmsContentTag");
    }
    public  static CmsContentTagManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsContentTagManager();
        }
        return _instance;
    }
}