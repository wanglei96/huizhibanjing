package BRP.service;

import BRP.model.BookManager;
import BRP.model.BookOrderItemManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScanBookEnabledServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String bookSn = JsonHelper.getString(jData, "bookSn");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        BookManager.getInstance().scanBookEnabledBook(companyId, bookSn);
        this.writeSuccessResponse(response);
    }
}
