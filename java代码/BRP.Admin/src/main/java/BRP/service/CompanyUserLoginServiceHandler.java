package BRP.service;

import BRP.data.ViewCompanyUser;
import BRP.model.CompanyUserManager;
import BRP.model.ViewCompanyUserManager;
import net.sf.json.JSONObject;
import strosoft.app.common.EncryptManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.ClientIpHelper;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sso.model.ActiveUserManager;
import strosoft.sys.data.SysLoginLog;
import strosoft.sys.model.AuthenticationManager;
import strosoft.sys.model.SysLoginLogManager;
import strosoft.sys.model.SysLoginTypeCodes;
import strosoft.sys.model.SysSettingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyUserLoginServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String mobilePhone = JsonHelper.getString(jData, "mobilePhone", "MobilePhone");
        String password = JsonHelper.getString(jData, "password", "Password");
        String applicationId = AuthenticationManager.getInstance().getParameterApplicationId(request);
        /*if (loginName == null || loginName.isEmpty()) {
            this.writeErrorResponse(response, "账号不能为空！");
            return;
        }*/
        ViewCompanyUser theViewCompanyUser = null;
        Integer companyId = JsonHelper.getInt(jData, "companyId", "CompanyId");
        String guid = JsonHelper.getString(jData, "guid");
        if (companyId != null && !StringHelper.isNullOrEmpty(guid)) {
            String storedRandomNum = CompanyUserManager.getInstance().storeRandomMap.get(companyId);
            if (storedRandomNum != null && storedRandomNum.equals(guid)) {
                String condition = String.format("company_id=%d and is_admin is true", companyId);
                List<ViewCompanyUser> lstViewCompanyUser = ViewCompanyUserManager.getInstance().getList(condition, "id", 1, 1);
                if (lstViewCompanyUser.size() > 0) {
                    theViewCompanyUser = lstViewCompanyUser.get(0);
                } else {
                    String errorMessage = "手机号不能为空！";
                    this.writeErrorLog(request, errorMessage);
                    this.writeErrorResponse(response, errorMessage);
                    return;
                }
            } else {
                String errorMessage = "手机号不能为空！";
                this.writeErrorLog(request, errorMessage);
                this.writeErrorResponse(response, errorMessage);
                return;
            }
        } else {
            if (mobilePhone == null || mobilePhone.isEmpty()) {
                String errorMessage = "手机号不能为空！";
                this.writeErrorLog(request, errorMessage);
                this.writeErrorResponse(response, errorMessage);
                return;
            }
            if (password == null || password.isEmpty()) {
                String errorMessage = "密码不能为空！";
                this.writeErrorLog(request, errorMessage);
                this.writeErrorResponse(response, errorMessage);
                return;
            }

            theViewCompanyUser = ViewCompanyUserManager.getInstance().getEntityByMobilePhone(mobilePhone);
            if (theViewCompanyUser == null) {
                String errorMessage = String.format("手机号%s不存在！", mobilePhone);
                this.writeErrorLog(request, errorMessage);
                this.writeErrorResponse(response, errorMessage);
                return;
            }
            if (!theViewCompanyUser.getEnabled()) {
                String errorMessage = String.format("用户'%s'已被禁用！", theViewCompanyUser.getName());
                this.writeErrorResponse(response, errorMessage);
                return;
            }
            //检查是否超过最大错误次数
            int maxWrongPasswordCount = SysSettingManager.getInstance().getIntSetting("MaxWrongPaswordCount", 5);
            if (theViewCompanyUser.getWrongPasswordCount() != null && theViewCompanyUser.getWrongPasswordCount() >= maxWrongPasswordCount) {
                //修改用户禁用
                String sql = String.format("update company_user set enabled=0 where id=%d", theViewCompanyUser.getId());
                MyBatisManager.getInstance().executeUpdate(sql);
                this.writeErrorResponse(response, "密码输入错误次数过多，账号已被锁定，请联系管理员处理！");
                return;
            }

            // 检查密码是不是正确
            Boolean isValid = EncryptManager.checkPassword(password, theViewCompanyUser.getPassword());
            if (!isValid) {
                CompanyUserManager.getInstance().increaseWrongPasswordCount(theViewCompanyUser.getId());
                int wrongPasswordCount = theViewCompanyUser.getWrongPasswordCount() == null ? 0 : theViewCompanyUser.getWrongPasswordCount();
                String errorMessage = String.format("密码不正确！已输入错误次数：%s，输入错误次数超过%s次后账号将被锁定！", wrongPasswordCount + 1, maxWrongPasswordCount);
                this.writeErrorResponse(response,
                        errorMessage);
                return;
            }

            //清空错误密码次数
            if (theViewCompanyUser.getWrongPasswordCount() != null && theViewCompanyUser.getWrongPasswordCount() > 0) {
                CompanyUserManager.getInstance().clearWrongPasswordCount(theViewCompanyUser.getId());
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("companyUser", theViewCompanyUser);
        // 是否开启登录状态验证
        Boolean userSessionCheckEnabled = AuthenticationManager.getInstance().getUserSessionCheckEnabled();
        if (userSessionCheckEnabled) {
            // 添加登录状态
            String ipAddress = AuthenticationManager.getInstance().getIpAddr(request);
            Integer userSessionOverTime = SysSettingManager.getInstance().getIntSetting("UserSessionOverTime", Integer.MAX_VALUE);
            ActiveUserManager.getInstance().addApplication(applicationId, userSessionCheckEnabled,
                    userSessionOverTime);
            Object token = AuthenticationManager.getInstance().addActiveUser(applicationId,
                    ((Integer) theViewCompanyUser.getId()).toString(), theViewCompanyUser.getLoginName(), ipAddress);
            if (null == token) {
                this.writeErrorResponse(response, "登录不成功，请重新登录");
                return;
            }
            // 返回token
            result.put("token", token);
        }
        //记录登录日志
        SysLoginLog newSysLoginLog = new SysLoginLog();
        newSysLoginLog.setSysLoginTypeCode(SysLoginTypeCodes.Company);
        newSysLoginLog.setAccountId(theViewCompanyUser.getId());
        newSysLoginLog.setIp(ClientIpHelper.getClientIp(request));
        newSysLoginLog.setCreateTime(DateHelper.getCurrentTimestamp());
        SysLoginLogManager.getInstance().add(newSysLoginLog);
        CompanyUserManager.getInstance().storeRandomMap.clear();
        this.writeSuccessResponse(response, result);
    }

    /**
     * 记录登录错误日志
     *
     * @param request
     * @param errorMessage
     * @throws Exception
     */
    private void writeErrorLog(HttpServletRequest request, String errorMessage) throws Exception {
        SysLoginLog newSysLoginLog = new SysLoginLog();
        newSysLoginLog.setSysLoginTypeCode(SysLoginTypeCodes.Company);
        newSysLoginLog.setIp(ClientIpHelper.getClientIp(request));
        newSysLoginLog.setErrorMessage(errorMessage);
        newSysLoginLog.setCreateTime(DateHelper.getCurrentTimestamp());
        SysLoginLogManager.getInstance().add(newSysLoginLog);
    }
}
