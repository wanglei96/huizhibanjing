package BRP.service;

import BRP.data.CompanyRole;
import BRP.data.CompanyUser;
import BRP.data.CompanyUserRole;
import BRP.model.CompanyRoleManager;
import BRP.model.CompanyUserManager;
import BRP.model.CompanyUserRoleManager;
import net.sf.json.JSONObject;
import org.springframework.util.CollectionUtils;
import strosoft.app.common.EncryptManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliverLoginServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String mobilePhone = JsonHelper.getString(jData, "mobile_phone", "mobilePhone", "MobilePhone");
        String password = JsonHelper.getString(jData, "password", "Password");
        if (mobilePhone == null || mobilePhone == "") {
            this.writeErrorResponse(response, "手机号不能为空！");
            return;
        }
        if (password == null || password == "") {
            this.writeErrorResponse(response, "密码不允许为空！");
            return;
        }

        String condition = "mobile_phone='" + mobilePhone + "'";
        List<CompanyUser> companyUserList = CompanyUserManager.getInstance().getList(condition);
        if (CollectionUtils.isEmpty(companyUserList)) {
            this.writeErrorResponse(response, String.format("手机号%s不存在！", mobilePhone));
            return;
        }
        CompanyUser companyUser = companyUserList.get(0);
        // 检查密码是不是正确
        Boolean isValid = EncryptManager.checkPassword(password, companyUser.getPassword());

        if (!isValid) {
            this.writeErrorResponse(response, "密码不正确！");
            return;
        }

        List<Map<Integer, String>> roleList = new ArrayList<>();
        // 获取当前用户权限
        List<CompanyRole> lstCompanyRole = CompanyRoleManager.getInstance().getListByCompanyUserId(companyUser.getId());
        List<String> lstRoleName = new ArrayList<>();
        for(CompanyRole curCompanyRole : lstCompanyRole){
            lstRoleName.add(curCompanyRole.getName());
        }
        Map<String, Object> result = new HashMap();
        result.put("deliverer", companyUser);
        result.put("role", lstRoleName);
        this.writeSuccessResponse(response, result);
    }
}
