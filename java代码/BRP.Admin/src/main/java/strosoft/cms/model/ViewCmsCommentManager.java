package strosoft.cms.model;
import strosoft.cms.data.ViewCmsCommentMaster;
public class ViewCmsCommentManager extends ViewCmsCommentMaster 
{
    private static ViewCmsCommentManager _instance;
    public  static ViewCmsCommentManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewCmsCommentManager();
        }
        return _instance;
    }
    private   ViewCmsCommentManager()    
    {
        this.setTypeName("ViewCmsComment");
    }
}