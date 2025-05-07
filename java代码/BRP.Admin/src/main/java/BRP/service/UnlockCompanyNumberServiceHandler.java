package BRP.service;

import BRP.data.CompanyUser;
import BRP.data.ViewBookOrder;
import BRP.model.CompanyUserManager;
import BRP.model.MessageManager;
import BRP.model.ViewBookOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnlockCompanyNumberServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        CompanyUserManager.getInstance().unlockCompanyNumber(companyId);
        this.writeSuccessResponse(response);
    }
}
