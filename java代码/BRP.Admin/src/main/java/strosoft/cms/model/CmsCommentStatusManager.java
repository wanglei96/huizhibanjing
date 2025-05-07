package strosoft.cms.model;
import strosoft.cms.data.CmsCommentStatusMaster;
public class CmsCommentStatusManager extends CmsCommentStatusMaster 
{
    private static CmsCommentStatusManager _instance;
    private CmsCommentStatusManager()    
    {
        this.setTableName("cms_comment_status");
        this.setTypeName("CmsCommentStatus");
    }
    public  static CmsCommentStatusManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsCommentStatusManager();
        }
        return _instance;
    }
}