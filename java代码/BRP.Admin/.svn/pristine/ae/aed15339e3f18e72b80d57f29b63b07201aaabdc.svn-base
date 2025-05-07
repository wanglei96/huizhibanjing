package BRP.model;
import BRP.data.CompanySettlementMaster;
public class CompanySettlementManager extends CompanySettlementMaster 
{
    private static CompanySettlementManager _instance;
    private CompanySettlementManager()    
    {
        this.setTableName("company_settlement");
        this.setTypeName("CompanySettlement");
    }
    public  static CompanySettlementManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanySettlementManager();
        }
        return _instance;
    }
}