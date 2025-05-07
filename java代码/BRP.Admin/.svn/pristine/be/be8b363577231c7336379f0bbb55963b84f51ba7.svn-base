package BRP.service;

import BRP.model.MemberManager;
import net.sf.json.JSONObject;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 获取预登记会员和注销会员的数量
 */
public class GetSignInAndCancelMemberCountServiceHandler extends GetListServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");

        Integer signCount = MemberManager.getInstance().getCount("is_sign = 1 and company_id = " + companyId);
        Integer cancelCount = MemberManager.getInstance().getCount("is_cancel = 1 and company_id = " + companyId);

        this.writeSuccessResponse(response, new HashMap<String,Object>(){{
            put("signCount",signCount);
            put("cancelCount",cancelCount);
        }});
    }
}
