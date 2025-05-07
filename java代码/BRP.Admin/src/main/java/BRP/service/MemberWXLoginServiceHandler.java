package BRP.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import BRP.data.Member;
import BRP.model.MemberManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import net.sf.json.JSONObject;

public class MemberWXLoginServiceHandler extends ServiceHandler {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        String wxaOpenId = JsonHelper.getString(jData, "wxaOpenId");
        String mobilePhone = JsonHelper.getString(jData, "mobilePhone");
        String unionId = JsonHelper.getString(jData, "unionId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        if ((wxaOpenId == null || wxaOpenId.isEmpty()) && (mobilePhone == null || mobilePhone.isEmpty())) {
            throw new Exception("登录失败，手机号和微信wxaOpenId不能同时为空");
        }
        Member theMember = MemberManager.getInstance().registerOrLoginMember(wxaOpenId, companyId, mobilePhone,unionId);
        this.writeSuccessResponse(response, theMember);
    }
}
