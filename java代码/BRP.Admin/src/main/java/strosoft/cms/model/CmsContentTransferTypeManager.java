package strosoft.cms.model;
import strosoft.cms.data.CmsContentTransferTypeMaster;
public class CmsContentTransferTypeManager extends CmsContentTransferTypeMaster 
{
    private static CmsContentTransferTypeManager _instance;
    private CmsContentTransferTypeManager()    
    {
        this.setTableName("cms_content_transfer_type");
        this.setTypeName("CmsContentTransferType");
    }
    public  static CmsContentTransferTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsContentTransferTypeManager();
        }
        return _instance;
    }
}