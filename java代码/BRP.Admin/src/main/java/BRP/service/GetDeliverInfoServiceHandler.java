package BRP.service;

import BRP.data.CompanyUser;
import BRP.model.CompanyManager;
import BRP.model.CompanyUserManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class GetDeliverInfoServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer delivererId = JsonHelper.getInt(jData, "delivererId");
        CompanyUser companyUser = CompanyUserManager.getInstance().getEntity(delivererId);
        if(companyUser.getCompanyId() == null){
            this.writeErrorResponse(response,"当前用户没有所属门店，请联系管理员！");
            return;
        }
        this.writeSuccessResponse(response, new HashMap<String, String>(){{
            put("name", companyUser.getName());
            put("mobilePhone", companyUser.getMobilePhone());
            put("companyName", CompanyManager.getInstance().getEntity(companyUser.getCompanyId()).getCompanyName());
        }});
    }
}
