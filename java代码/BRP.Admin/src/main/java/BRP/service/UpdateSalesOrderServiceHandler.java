package BRP.service;

import BRP.data.Company;
import BRP.data.SalesOrderItem;
import BRP.model.CompanyManager;
import BRP.model.SalesOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.UpdateServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UpdateSalesOrderServiceHandler extends UpdateServiceHandler {
    @Override
    protected void postProcess(ServiceContext updateServiceContext) throws Exception {
/*        HttpServletRequest request = updateServiceContext.getRequest();
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        List<SalesOrderItem> salesOrderItemList = JsonHelper.getJavaList(SalesOrderItem.class, jData, "salesOrderItemList");
        for (SalesOrderItem salesOrderItem : salesOrderItemList) {
            SalesOrderItemManager.getInstance().update(updateServiceContext.session, salesOrderItem);
        }*/
    }
}
