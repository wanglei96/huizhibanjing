package BRP.service;

import BRP.data.CompanyUser;
import BRP.model.CompanyUserManager;
import net.sf.json.JSONObject;
import strosoft.app.common.EncryptManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangeCompanyUserPasswordServiceHandler extends ServiceHandler {
    @SuppressWarnings("rawtypes")
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);

        int companyUserId = JsonHelper.getInt(jData, "companyUserId");
        String oldPassword = JsonHelper.getString(jData, "oldPassword");
        String newPassword = JsonHelper.getString(jData, "password");
        String rePassword = JsonHelper.getString(jData, "rePassword");

        CompanyUser theCompanyUser = CompanyUserManager.getInstance().getEntity(companyUserId);
        // 检查原密码是不是正确
        Boolean isValid = EncryptManager.checkPassword(oldPassword,
                theCompanyUser.getPassword());
        if (!isValid) {
            this.writeErrorResponse(response,
                    String.format("原密码不正确！"));
            return;
        }
        //判断两次输入是否一致
        if (!newPassword.equals(rePassword)) {
            this.writeErrorResponse(response, "两次密码不一致！");
            return;
        }
        //执行sql
        CompanyUserManager.getInstance().setCompanyUserPassword(companyUserId, newPassword);
        this.writeSuccessResponse(response);

    }
}
