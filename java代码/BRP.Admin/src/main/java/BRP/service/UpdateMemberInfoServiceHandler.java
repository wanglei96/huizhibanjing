package BRP.service;

import BRP.data.SalesOrderItem;
import BRP.model.SalesOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.UpdateServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

public class UpdateMemberInfoServiceHandler extends UpdateServiceHandler {
    @Override
    protected void postProcess(ServiceContext updateServiceContext) throws Exception {
        HttpServletRequest request = updateServiceContext.getRequest();
        JSONObject jData = getRequestData(request);
        Integer memberCardTypeId = JsonHelper.getInt(jData, "memberCardTypeId");

        Timestamp effectiveDateText = JsonHelper.getTimestamp(jData, "effectiveDateText");
        Timestamp expireDateText = JsonHelper.getTimestamp(jData, "expireDateText");
    }
}
