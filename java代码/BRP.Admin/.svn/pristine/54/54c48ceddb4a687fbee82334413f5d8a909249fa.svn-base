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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 会员续费
 */
public class AddMemberCardRenewLogServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisManager.getInstance().openSession();
            JSONObject jData = this.getRequestData(request);
            Integer companyId = JsonHelper.getInt(jData, "companyId"); // 门店id
            Integer memberId = JsonHelper.getInt(jData, "memberId"); // 会员id
            Integer memberCardId = JsonHelper.getInt(jData, "memberCardId"); // 会员卡id
            Integer activityId = JsonHelper.getInt(jData, "activityId"); // 活动id
            BigDecimal oldAmount = JsonHelper.getDecimal(jData, "oldAmount"); // 原续费价格
            BigDecimal amount = JsonHelper.getDecimal(jData, "amount"); // 续费价格
            BigDecimal newAmount = JsonHelper.getDecimal(jData, "newAmount"); // 新续费价格
            String paymentMethodCode = JsonHelper.getString(jData, "paymentMethodCode"); // 支付方式
            Integer employeeId = JsonHelper.getInt(jData, "employeeId"); // 办理人
            String remark = JsonHelper.getString(jData, "remark"); // 支付方式
            String expireDate = JsonHelper.getString(jData, "expireDate"); // 到期时间
            Integer buyDays = JsonHelper.getInt(jData, "buyDays"); // 购买天数
            Integer giftDays = JsonHelper.getInt(jData, "giftDays"); // 赠送天数
            Integer loanableNum = JsonHelper.getInt(jData, "loanableNum"); // 可借本书
            Integer giftScore = JsonHelper.getInt(jData, "giftScore"); // 新增积分
            Integer giftStopCardQuantity = JsonHelper.getInt(jData, "giftStopCardQuantity"); // 停卡数量

            MemberCardRenewLog memberCardRenewLog = new MemberCardRenewLog();
            memberCardRenewLog.setMemberId(memberId);
            memberCardRenewLog.setMemberCardId(memberCardId);
            memberCardRenewLog.setActivityId(activityId);
            memberCardRenewLog.setOldAmount(oldAmount);
            memberCardRenewLog.setAmount(amount);
            memberCardRenewLog.setNewAmount(newAmount);
            memberCardRenewLog.setPaymentMethodCode(paymentMethodCode);
            memberCardRenewLog.setEmployeeId(employeeId);
            memberCardRenewLog.setRemark(remark);
            memberCardRenewLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
            MemberCardRenewLogManager.getInstance().add(sqlSession, memberCardRenewLog);

            MemberCard memberCard = MemberCardManager.getInstance().getEntity(memberCardId);
            Timestamp agoExpireDate = memberCard.getExpireDate();
            memberCard.setMembershipAmount(newAmount);
            memberCard.setMaxBorrowableQuantity(loanableNum);
            memberCard.setExpireDate(Timestamp.valueOf(expireDate));
            if(giftStopCardQuantity != null && giftStopCardQuantity != 0){
                if(memberCard.getStoppableCardQuantity() != null){
                    memberCard.setStoppableCardQuantity(memberCard.getStoppableCardQuantity() + giftStopCardQuantity);
                }else{
                    memberCard.setStoppableCardQuantity(giftStopCardQuantity);
                }
                //添加停卡数量记录
                MemberCardDisableLog newMemberCardDisableLog = new MemberCardDisableLog();
                newMemberCardDisableLog.setMemberId(memberId);
                newMemberCardDisableLog.setMemberCardId(memberCardId);
                newMemberCardDisableLog.setStopCardNum(giftStopCardQuantity);
                newMemberCardDisableLog.setApplyTime(DateHelper.getCurrentTimestamp());
                newMemberCardDisableLog.setStatus("givestopcard");
                newMemberCardDisableLog.setType("augment");
                newMemberCardDisableLog.setSource("MemberRenew");
                MemberCardDisableLogManager.getInstance().add(sqlSession, newMemberCardDisableLog);
            }
            MemberCardManager.getInstance().update(sqlSession, memberCard);

            if (giftScore != null && giftScore != 0) {
                String sql = String.format("UPDATE member SET score = COALESCE(score, 0) + %d WHERE id=%d", giftScore, memberId);
                MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
                //添加会员积分记录
                MemberScore newMemberScore = new MemberScore();
                newMemberScore.setMemberId(memberId);
                newMemberScore.setScore(giftScore);
                newMemberScore.setMemberScoreTypeCode("EnterIntoActivities");
                newMemberScore.setScoreTime(DateHelper.getCurrentTimestamp());
                MemberScoreManager.getInstance().add(sqlSession, newMemberScore);
            }


            //添加pay_order账单
            PayOrder newPayOrder = new PayOrder();
            newPayOrder.setCompanyId(companyId);
            newPayOrder.setAmount(amount);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            newPayOrder.setMemberId(memberId);
            Date now = new Date();
            String payOrderNo = sdf.format(now);
            newPayOrder.setPayOrderNo(payOrderNo);
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            newPayOrder.setBusinessDate(currentTimestamp);
            newPayOrder.setPaymentItemCode("MembershipFee");
            newPayOrder.setPayTime(currentTimestamp);
            newPayOrder.setOperatorId(employeeId);
            newPayOrder.setPaymentMethodCode(paymentMethodCode);
            newPayOrder.setIsFinished(true);
            newPayOrder.setIsPaid(true);
            PayOrderManager.getInstance().add(sqlSession, newPayOrder);

            // 会员卡变更记录
            MemberCardServiceTimeLog memberCardServiceTimeLog = new MemberCardServiceTimeLog();
            memberCardServiceTimeLog.setMemberId(memberId);
            memberCardServiceTimeLog.setEffectiveTime(memberCard.getEffectiveDate());
            memberCardServiceTimeLog.setExpiryTime(memberCard.getExpireDate());
            memberCardServiceTimeLog.setMemberCardServiceTimeTypeCode("MemberRenew");
            memberCardServiceTimeLog.setDays(buyDays);
            memberCardServiceTimeLog.setGiftDays(giftDays);
            memberCardServiceTimeLog.setCost(amount);
            memberCardServiceTimeLog.setPaymentMethodCode(paymentMethodCode);
            memberCardServiceTimeLog.setRemark(remark);
            memberCardServiceTimeLog.setAgoExpiryTime(agoExpireDate);
            memberCardServiceTimeLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
            MemberCardServiceTimeLogManager.getInstance().add(sqlSession, memberCardServiceTimeLog);
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