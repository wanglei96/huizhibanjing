package BRP.model;
import BRP.data.CompanyImageMaster;
public class CompanyImageManager extends CompanyImageMaster 
{
    private static CompanyImageManager _instance;
    private CompanyImageManager()    
    {
        this.setTableName("company_image");
        this.setTypeName("CompanyImage");
    }
    public  static CompanyImageManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanyImageManager();
        }
        return _instance;
    }
}