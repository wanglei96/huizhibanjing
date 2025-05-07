package BRP.service;

import BRP.data.Company;
import BRP.model.CompanyManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCompanyUserNoServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Company theCompany = CompanyManager.getInstance().getEntity(companyId);
        String companyUserNo = CompanyManager.getInstance().createCompanyUserNo(theCompany);
        writeSuccessResponse(response, companyUserNo);

    }
}
