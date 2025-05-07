package strosoft.cms.model;
import strosoft.cms.data.CmsCommentTypeMaster;
public class CmsCommentTypeManager extends CmsCommentTypeMaster 
{
    private static CmsCommentTypeManager _instance;
    private CmsCommentTypeManager()    
    {
        this.setTableName("cms_comment_type");
        this.setTypeName("CmsCommentType");
    }
    public  static CmsCommentTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsCommentTypeManager();
        }
        return _instance;
    }
}