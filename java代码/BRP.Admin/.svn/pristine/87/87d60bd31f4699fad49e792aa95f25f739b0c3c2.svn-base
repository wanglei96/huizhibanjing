package BRP.service;

import BRP.data.CompanyUser;
import BRP.data.MemberScore;
import BRP.model.CompanyManager;
import BRP.model.CompanyUserManager;
import BRP.model.MemberScoreManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BatchUpdateMemberScoreServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        List<Integer> memberIds = JsonHelper.getIntArray(jData, "memberIds");
        Integer score = JsonHelper.getInt(jData, "score");
        String memberScoreTypeCode = JsonHelper.getString(jData, "memberScoreTypeCode");
        MemberScoreManager.getInstance().batchUpdateMemberScore(memberIds, score, memberScoreTypeCode);
        this.writeSuccessResponse(response);
    }
}
