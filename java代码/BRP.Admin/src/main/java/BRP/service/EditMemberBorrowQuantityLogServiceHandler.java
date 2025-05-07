package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * 可阅读数量日志记录
 */
public class EditMemberBorrowQuantityLogServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer memberCardId = JsonHelper.getInt(jData,"id");
        Integer memberId = JsonHelper.getInt(jData,"memberId");
        Integer memberCardTypeId = JsonHelper.getInt(jData,"memberCardTypeId");
        Integer quantity = JsonHelper.getInt(jData,"quantity");
        String remark = JsonHelper.getString(jData,"borrowRemark");
        String memberBorrowQuantityTypeCode = JsonHelper.getString(jData,"memberBorrowQuantityTypeCode");
        String activityName = JsonHelper.getString(jData,"activityName");
        Timestamp startDate = JsonHelper.getTimestamp(jData,"startDate");
        Timestamp endDate = JsonHelper.getTimestamp(jData,"endDate");
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisManager.getInstance().openSession();

            // 创建可借阅数量日志
            MemberBorrowQuantityLog memberBorrowQuantityLog = new MemberBorrowQuantityLog();
            memberBorrowQuantityLog.setMemberId(memberId);
            memberBorrowQuantityLog.setQuantity(quantity);
            memberBorrowQuantityLog.setRemark(remark);
            memberBorrowQuantityLog.setActivityName(activityName);
            memberBorrowQuantityLog.setStartDate(startDate);
            memberBorrowQuantityLog.setEndDate(endDate);
            memberBorrowQuantityLog.setMemberBorrowQuantityTypeCode(memberBorrowQuantityTypeCode);
            MemberBorrowQuantityLogManager.getInstance().add(sqlSession, memberBorrowQuantityLog);


            // 变更会员卡可借图书数量
            MemberCard memberCard = MemberCardManager.getInstance().getEntity(sqlSession,memberCardId);

            // 会员卡变更记录
            MemberCardServiceTimeLog memberCardServiceTimeLog = new MemberCardServiceTimeLog();
            memberCardServiceTimeLog.setMemberId(memberId);
            memberCardServiceTimeLog.setEffectiveTime(startDate);
            memberCardServiceTimeLog.setExpiryTime(endDate);
            memberCardServiceTimeLog.setMemberCardServiceTimeTypeCode("AddBorrowNumber");
            memberCardServiceTimeLog.setDays(quantity);
            memberCardServiceTimeLog.setRemark(remark);
            memberCardServiceTimeLog.setAddTime(DateHelper.getCurrentTimestamp());
            MemberCardServiceTimeLogManager.getInstance().add(sqlSession,memberCardServiceTimeLog);
            sqlSession.commit();
            this.writeSuccessResponse(response);
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
