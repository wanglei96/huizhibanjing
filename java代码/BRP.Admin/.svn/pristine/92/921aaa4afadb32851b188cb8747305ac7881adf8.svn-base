package BRP.service;

import BRP.data.Company;
import BRP.data.SalesOrder;
import BRP.data.SalesOrderItem;
import BRP.data.SalesOrderStatusCodes;
import BRP.model.CompanyManager;
import BRP.model.SalesOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AddSalesOrderServiceHandler extends AddServiceHandler {
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        SalesOrder salesOrder = (SalesOrder) newDataEntity;
        salesOrder.setSalesOrderStatusCode(SalesOrderStatusCodes.Delivering);
        salesOrder.setPayTime(DateHelper.getCurrentTimestamp());
        salesOrder.setIsPaid(true);
        return salesOrder;
    }

    @Override
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        HttpServletRequest request = newAddServiceContext.getRequest();
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        //添加子订单
        List<SalesOrderItem> salesOrderItemList = JsonHelper.getJavaList(SalesOrderItem.class, jData, "salesOrderItemList");
        for (SalesOrderItem salesOrderItem : salesOrderItemList) {
            salesOrderItem.setSalesOrderId(newAddServiceContext.newId);
        }
        /*Company company = CompanyManager.getInstance().getEntity(newAddServiceContext.session, companyId);
        company.setBookboxInventoryQuantity((company.getBookboxInventoryQuantity() == null ? 0 : company.getBookboxInventoryQuantity()) + salesOrderItemList.get(0).getQuantity().intValue());
        CompanyManager.getInstance().update(newAddServiceContext.session, company);*/
        SalesOrderItemManager.getInstance().addList(newAddServiceContext.session, salesOrderItemList);
    }
}
