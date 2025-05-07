package strosoft.cms.model;
import strosoft.cms.data.CmsTemplateTypeMaster;
public class CmsTemplateTypeManager extends CmsTemplateTypeMaster 
{
    private static CmsTemplateTypeManager _instance;
    private CmsTemplateTypeManager()    
    {
        this.setTableName("cms_template_type");
        this.setTypeName("CmsTemplateType");
    }
    public  static CmsTemplateTypeManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CmsTemplateTypeManager();
        }
        return _instance;
    }
}