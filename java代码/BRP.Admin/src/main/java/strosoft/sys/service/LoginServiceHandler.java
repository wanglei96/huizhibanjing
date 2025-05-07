package strosoft.sys.service;

import net.sf.json.JSONObject;
import strosoft.app.common.EncryptManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.ClientIpHelper;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sso.model.ActiveUserManager;
import strosoft.sys.data.SysLoginLog;
import strosoft.sys.data.ViewSysUser;
import strosoft.sys.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        String loginName = JsonHelper.getString(jData, "login_name",
                "loginName", "LoginName");
        String applicationId = AuthenticationManager.getInstance().getParameterApplicationId(request);
        if (loginName == null || loginName == "") {
            this.writeErrorResponse(response, "账号不能为空！");
            return;
        }
        // 检查登录账号是不是存在
        Boolean exists = SysUserManager.getInstance()
                .existsLoginName(loginName);
        if (!exists) {
            this.writeErrorResponse(response,
                    String.format("账号%s不存在！", loginName));
            return;
        }
        ViewSysUser theSysUser = ViewSysUserManager.getInstance().getEntityByLoginName(
                loginName);
        // 检查用户是否被禁用
        Boolean enabled = theSysUser.getEnabled();
        if (enabled == null || !enabled) {
            this.writeErrorResponse(response,
                    String.format("该用户已被禁用，请联系管理员处理！", loginName));
            return;
        }
        //检查是否超过最大错误次数
        int maxWrongPasswordCount = SysSettingManager.getInstance().getIntSetting("MaxWrongPaswordCount", 5);
        if (theSysUser.getWrongPasswordCount() != null && theSysUser.getWrongPasswordCount() >= maxWrongPasswordCount) {
            String sql = String.format("update sys_user set enabled = 0 where id = %s", theSysUser.getId());
            MyBatisManager.getInstance().executeUpdate(sql);
            this.writeErrorResponse(response, "密码输入错误次数过多，账号已被锁定，请联系管理员处理！");
            return;
        }
        // 检查密码是不是正确
        String password = JsonHelper.getString(jData, "password", "Password");
        Boolean isValid = EncryptManager.checkPassword(password,
                theSysUser.getPassword());
        if (!isValid) {
            SysUserManager.getInstance().increaseWrongPasswordCount(theSysUser.getId());
            int wrongPasswordCount = theSysUser.getWrongPasswordCount() == null ? 0 : theSysUser.getWrongPasswordCount();
            String errorMessage = String.format("密码不正确！已输入错误次数：%s，输入错误次数超过%s次后账号将被锁定！", wrongPasswordCount + 1, maxWrongPasswordCount);
            this.writeErrorResponse(response, errorMessage);
            return;
        }

        //清空错误密码次数
        if (theSysUser.getWrongPasswordCount() != null && theSysUser.getWrongPasswordCount() > 0) {
            SysUserManager.getInstance().clearWrongPasswordCount(theSysUser.getId());
        }
        //记录登录日志
        this.addSysLoginLog(request, theSysUser.getId());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("sysUser", theSysUser);
        //是否开启登录状态验证
        Boolean userSessionCheckEnabled = AuthenticationManager.getInstance().getUserSessionCheckEnabled();
        if (userSessionCheckEnabled) {
            // 添加登录状态
            String ipAddress = AuthenticationManager.getInstance().getIpAddr(request);
            Integer userSessionOverTime = SysSettingManager.getInstance().getIntSetting("UserSessionOverTime", Integer.MAX_VALUE);
            ActiveUserManager.getInstance().addApplication(applicationId, userSessionCheckEnabled,
                    userSessionOverTime);
            Object token = AuthenticationManager.getInstance().addActiveUser(applicationId, ((Integer) theSysUser.getId()).toString(), theSysUser.getLoginName(), ipAddress);
            if (null == token) {
                this.writeErrorResponse(response, "登录不成功，请重新登录");
                return;
            }
            //返回token
            result.put("token", token);
        }

        this.writeSuccessResponse(response, result);

    }

    /**
     * 记录登录日志
     */
    private void addSysLoginLog(HttpServletRequest request, int accountId) throws Exception {
        SysLoginLog newSysLoginLog = new SysLoginLog();
        newSysLoginLog.setSysLoginTypeCode(SysLoginTypeCodes.System);
        newSysLoginLog.setAccountId(accountId);
        String ip = ClientIpHelper.getClientIp(request);
        newSysLoginLog.setIp(ip);
        newSysLoginLog.setCreateTime(DateHelper.getCurrentTimestamp());
        SysLoginLogManager.getInstance().add(newSysLoginLog);
    }
}
