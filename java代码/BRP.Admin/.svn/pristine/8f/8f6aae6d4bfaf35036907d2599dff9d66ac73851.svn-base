package BRP.service;

import BRP.data.CompanyProductStocks;
import BRP.data.SalesOrder;
import BRP.data.SalesOrderItem;
import BRP.model.CompanyProductStocksManager;
import BRP.model.SalesOrderItemManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.UpdateServiceHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 完成订单
 */
public class SalesOrderFinishedServiceHandler extends UpdateServiceHandler {
    @Override
    protected DataEntity processDataEntity(ServiceContext updateServiceContext, DataEntity newDataEntity)
            throws Exception {
        //SalesOrder newSalesOrder = (SalesOrder) newDataEntity;
        return newDataEntity;
    }

    @Override
    protected void postProcess(ServiceContext updateServiceContext) throws Exception {
        HttpServletRequest request = updateServiceContext.getRequest();
        JSONObject jData = getRequestData(request);
        SqlSession sqlSession = updateServiceContext.getSession();
        SalesOrder newSalesOrder = (SalesOrder) updateServiceContext.getDataEntity();
        List<SalesOrderItem> lstSalesOrderItem = SalesOrderItemManager.getInstance().getListBySalesOrderId(sqlSession, newSalesOrder.getId());
        for (SalesOrderItem salesOrderItem : lstSalesOrderItem) {
            //获取产品库存
            CompanyProductStocks theCompanyProductStocks = CompanyProductStocksManager.getInstance().
                    getEntityByProductIdAndCompanyId(sqlSession,salesOrderItem.getProductId(), newSalesOrder.getCompanyId());
            if (theCompanyProductStocks != null) {
                if(theCompanyProductStocks.getQuantity()==null){
                    theCompanyProductStocks.setQuantity(salesOrderItem.getQuantity().intValue());
                }else{
                    theCompanyProductStocks.setQuantity(theCompanyProductStocks.getQuantity()+salesOrderItem.getQuantity().intValue());
                }
                CompanyProductStocksManager.getInstance().update(sqlSession, theCompanyProductStocks);
            }else{
                CompanyProductStocks newCompanyProductStocks = new CompanyProductStocks();
                newCompanyProductStocks.setCompanyId(newSalesOrder.getCompanyId());
                newCompanyProductStocks.setProductId(salesOrderItem.getProductId());
                newCompanyProductStocks.setQuantity(salesOrderItem.getQuantity().intValue());
                CompanyProductStocksManager.getInstance().add(sqlSession, newCompanyProductStocks);
            }
        }
    }
}
