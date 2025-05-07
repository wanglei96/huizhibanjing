package BRP.service;

import BRP.data.MemberCard;
import BRP.data.MemberCardDisableLog;
import BRP.data.MemberCardServiceTimeLog;
import BRP.model.MemberCardDisableLogManager;
import BRP.model.MemberCardServiceTimeLogManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddMemberCardDisableTimeChangeRecordServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            String deleteSql = "DELETE FROM member_card_service_time_log WHERE member_card_service_time_type_code = 'StopCard'";
            MyBatisManager.getInstance().executeDelete(sqlSession, deleteSql);
            String condition = "is_relieve is true and type = 'decrease'";
            List<MemberCardDisableLog> lstMemberCardDisableLog = MemberCardDisableLogManager.getInstance().getList(sqlSession, condition);
            List<MemberCardServiceTimeLog> lstMemberCardServiceTimeLog = new ArrayList<>();
            for (MemberCardDisableLog memberCardDisableLog : lstMemberCardDisableLog){
                //添加会员时长变更记录
                MemberCardServiceTimeLog newMemberCardServiceTimeLog = new MemberCardServiceTimeLog();
                newMemberCardServiceTimeLog.setMemberId(memberCardDisableLog.getMemberId());
                newMemberCardServiceTimeLog.setDays(memberCardDisableLog.getDays());
                newMemberCardServiceTimeLog.setMemberCardServiceTimeTypeCode("StopCard");
                newMemberCardServiceTimeLog.setAddTime(DateHelper.getCurrentTimestamp());
                newMemberCardServiceTimeLog.setEffectiveTime(DateHelper.getCurrentTimestamp());
                newMemberCardServiceTimeLog.setMemberCardServiceTimeGiftTypeCode("StopCardGift");
                lstMemberCardServiceTimeLog.add(newMemberCardServiceTimeLog);
            }
            MemberCardServiceTimeLogManager.getInstance().addList(sqlSession, lstMemberCardServiceTimeLog);
            writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
