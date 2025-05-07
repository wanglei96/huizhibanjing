package BRP.service;

import BRP.data.CompanyUser;
import BRP.model.CompanyUserManager;
import net.sf.json.JSONObject;
import strosoft.app.common.EncryptManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliverUpdatePasswordServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);

        Integer id = JsonHelper.getInt(jData, "id");
        String oldPassword = JsonHelper.getString(jData, "oldPassword");
        String newPassword = JsonHelper.getString(jData, "newPassword");
        String confirmPassword = JsonHelper.getString(jData, "confirmPassword");

        if (oldPassword == null || oldPassword == "") {
            this.writeErrorResponse(response, "原密码不能为空！");
            return;
        }

        if (newPassword == null || newPassword == "") {
            this.writeErrorResponse(response, "新密码不能为空！");
            return;
        }

        if (confirmPassword == null || confirmPassword == "") {
            this.writeErrorResponse(response, "确认密码不能为空！");
            return;
        }

        CompanyUser companyUser = CompanyUserManager.getInstance().getEntity(id);


        // 检查密码是不是正确
        Boolean isValid = EncryptManager.checkPassword(oldPassword, companyUser.getPassword());

        if (!isValid) {
            this.writeErrorResponse(response, "原密码不正确！");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            this.writeErrorResponse(response, "两次密码输入不一致！");
            return;
        }

        // 密码加密
        companyUser.setPassword(EncryptManager.encryptPassword(newPassword));
        CompanyUserManager.getInstance().update(companyUser);
        this.writeSuccessResponse(response, "密码修改成功");
    }
}
