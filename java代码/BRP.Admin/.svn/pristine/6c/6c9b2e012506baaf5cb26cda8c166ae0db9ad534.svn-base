package BRP.service;

import BRP.model.CompanyUserManager;
import BRP.model.SettlementOrderManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import java.util.UUID;

public class SetGUIDServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String guid = CompanyUserManager.getInstance().getGuid(companyId);

        this.writeSuccessResponse(response, guid);
    }
}
