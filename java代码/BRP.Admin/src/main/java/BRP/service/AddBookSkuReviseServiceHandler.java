package BRP.service;

import BRP.data.BookSkuReviseItem;
import BRP.data.Company;
import BRP.data.SalesOrderItem;
import BRP.model.BookSkuReviseItemManager;
import BRP.model.CompanyManager;
import BRP.model.SalesOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddBookSkuReviseServiceHandler extends AddServiceHandler {
    @Override
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        HttpServletRequest request = newAddServiceContext.getRequest();
        JSONObject jData = getRequestData(request);
        //添加子订单
        List<BookSkuReviseItem> bookSkuReviseItemList = JsonHelper.getJavaList(BookSkuReviseItem.class, jData, "bookSkuReviseItemList");
        for (BookSkuReviseItem bookSkuReviseItem : bookSkuReviseItemList) {
            bookSkuReviseItem.setBookSkuReviseId(newAddServiceContext.newId);
            bookSkuReviseItem.setApproveStatusCode("Approving");
        }
        BookSkuReviseItemManager.getInstance().addList(newAddServiceContext.session, bookSkuReviseItemList);
    }
}
