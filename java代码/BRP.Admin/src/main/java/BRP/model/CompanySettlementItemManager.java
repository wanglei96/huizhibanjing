package BRP.model;
import BRP.data.CompanySettlementItemMaster;
public class CompanySettlementItemManager extends CompanySettlementItemMaster 
{
    private static CompanySettlementItemManager _instance;
    private CompanySettlementItemManager()    
    {
        this.setTableName("company_settlement_item");
        this.setTypeName("CompanySettlementItem");
    }
    public  static CompanySettlementItemManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new CompanySettlementItemManager();
        }
        return _instance;
    }
}