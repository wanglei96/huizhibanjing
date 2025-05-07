package BRP.model;

import BRP.data.CompanyProductStocks;
import BRP.data.CompanyProductStocksMaster;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CompanyProductStocksManager extends CompanyProductStocksMaster {
    private static CompanyProductStocksManager _instance;

    private CompanyProductStocksManager() {
        this.setTableName("company_product_stocks");
        this.setTypeName("CompanyProductStocks");
    }

    public static CompanyProductStocksManager getInstance() {
        if (_instance == null) {
            _instance = new CompanyProductStocksManager();
        }
        return _instance;
    }

    /**
     * 获取产品库存
     *
     * @param productId
     * @param companyId
     * @return
     */
    public CompanyProductStocks getEntityByProductIdAndCompanyId(SqlSession sqlSession, Integer productId, Integer companyId) {
        String condition = "product_id = " + productId + " and company_id = " + companyId;
        List<CompanyProductStocks> lstCompanyProductStocks = this.getList(sqlSession, condition);
        if (lstCompanyProductStocks.size() > 0) {
            return lstCompanyProductStocks.get(0);
        }
        return null;
    }
}