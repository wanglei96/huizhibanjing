package BRP.model;

import BRP.data.CompanyProductStocks;
import BRP.data.ViewCompanyProductStocks;
import BRP.data.ViewCompanyProductStocksMaster;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ViewCompanyProductStocksManager extends ViewCompanyProductStocksMaster {
    private static ViewCompanyProductStocksManager _instance;

    public static ViewCompanyProductStocksManager getInstance() {
        if (_instance == null) {
            _instance = new ViewCompanyProductStocksManager();
        }
        return _instance;
    }

    private ViewCompanyProductStocksManager() {
        this.setTypeName("ViewCompanyProductStocks");
    }

    public ViewCompanyProductStocks getEntityByCodeAndCompanyId(SqlSession sqlSession, String code, Integer companyId) {
        String condition = String.format("product_code ='%s' and company_id=%d", code, companyId);
        List<ViewCompanyProductStocks> lstCompanyProductStocks = this.getList(sqlSession, condition);
        if (lstCompanyProductStocks.size() > 0) {
            return lstCompanyProductStocks.get(0);
        }
        return null;
    }
}