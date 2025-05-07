package BRP.service;

import BRP.data.ActivityServiceTime;
import BRP.data.MemberCardServiceTimeLog;
import BRP.model.ActivityServiceTimeManager;
import BRP.model.MemberCardManager;
import BRP.model.MemberCardServiceTimeLogManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BatchAddActivityServiceTimeServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject requestData = getRequestData(request);
        Integer days = JsonHelper.getInt(requestData, "days");
        Integer activityId = JsonHelper.getInt(requestData, "activityId");
        String remark = JsonHelper.getString(requestData, "remark");
        String activityFreeTypeCode = JsonHelper.getString(requestData, "activityFreeTypeCode");
        Integer companyId = JsonHelper.getInt(requestData, "companyId");

        JSONArray memberCardIdArr = JsonHelper.getJSONArray(requestData, "memberCardIdArr");
        List<String> memberCardIdList = new ArrayList<>();

        List<ActivityServiceTime> newActivityServiceTimelist = new ArrayList<>();

        SqlSession sqlSession = MyBatisManager.getInstance().openSession();

        try {
            List<MemberCardServiceTimeLog> newLstMemberCardServiceTimeLog = new ArrayList<>();
            if (memberCardIdArr != null && memberCardIdArr.size() > 0) {
                for (int i = 0; i < memberCardIdArr.size(); i++) {
                    JSONObject jsonObject = memberCardIdArr.getJSONObject(i);
                    memberCardIdList.add(JsonHelper.getInt(jsonObject, "memberCardId").toString());
                    //获取到期时间
                    Timestamp expireDate = JsonHelper.getTimestamp(jsonObject, "expireDate");
                    Timestamp addExpireDate = DateHelper.addDays(expireDate, days);
                    ActivityServiceTime newActivityServiceTime = new ActivityServiceTime();
                    newActivityServiceTime.setMemberCardId(JsonHelper.getInt(jsonObject, "memberCardId"));
                    newActivityServiceTime.setActivityId(activityId);
                    newActivityServiceTime.setActivityFreeTypeCode(activityFreeTypeCode);
                    newActivityServiceTime.setRemark(remark);
                    newActivityServiceTime.setDays(days);
                    newActivityServiceTime.setExpireDate(addExpireDate);
                    newActivityServiceTime.setCompanyId(companyId);

                    newActivityServiceTimelist.add(newActivityServiceTime);

                    MemberCardServiceTimeLog newMemberLog = new MemberCardServiceTimeLog();
                    newMemberLog.setActivityId(activityId);
                    newMemberLog.setAddTime(DateHelper.getCurrentTimestamp());
                    newMemberLog.setMemberId(JsonHelper.getInt(jsonObject, "memberId"));
                    newMemberLog.setDays(days);
                    newMemberLog.setMemberCardServiceTimeTypeCode("ActivityBatcGift");
                    newMemberLog.setMemberCardServiceTimeGiftTypeCode("ActivityBatcGift");
                    newMemberLog.setExpiryTime(addExpireDate);
                    newMemberLog.setAgoExpiryTime(expireDate);
                    newLstMemberCardServiceTimeLog.add(newMemberLog);
                }
            }
            MemberCardServiceTimeLogManager.getInstance().addList(sqlSession, newLstMemberCardServiceTimeLog);
            ActivityServiceTimeManager.getInstance().addList(sqlSession, newActivityServiceTimelist);
            //批量增加曾加会员卡失效日期
            MemberCardManager.getInstance().delayExpireDate(sqlSession, memberCardIdList, days);

            MyBatisManager.getInstance().commitSession(sqlSession);
            writeSuccessResponse(response);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);

            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}
