package strosoft.cms.model;
import strosoft.cms.data.CmsSiteTypeMaster;
public class CmsSiteTypeManager extends CmsSiteTypeMaster 
{
    private static CmsSiteTypeManager _instance;
    private CmsSiteTypeManager()    
    {
        this.setTableName("cms_site_type");
        this.setTypeName("CmsSiteType");
    }
    public  static CmsSiteTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsSiteTypeManager();
        }
        return _instance;
    }
}