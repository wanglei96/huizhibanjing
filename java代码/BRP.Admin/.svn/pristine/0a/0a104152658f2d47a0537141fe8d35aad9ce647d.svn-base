package BRP.model;

import BRP.data.*;
import BRP.data.MemberScoreType;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.DateHelper;
import strosoft.app.util.Tracer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class RetailOrderManager extends RetailOrderMaster {
    private static RetailOrderManager _instance;

    private RetailOrderManager() {
        this.setTableName("retail_order");
        this.setTypeName("RetailOrder");
    }

    public static RetailOrderManager getInstance() {
        if (_instance == null) {
            _instance = new RetailOrderManager();
        }
        return _instance;
    }

    /**
     * 支付回调
     *
     * @param outTradeNo
     * @param wxPayTransactionId
     */
    public void payRetailOrderNotify(String outTradeNo, String wxPayTransactionId) throws Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            // 修改订单信息
            RetailOrder theRetailOrder = this.getEntityByRetailOrderNo(sqlSession, outTradeNo);
            if (theRetailOrder == null) {
                Tracer.writeLine(String.format("未找到订单编号为%s的订单！", outTradeNo));
                throw new Exception(String.format("未找到订单编号为%s的订单！", outTradeNo));
            }
            if (!theRetailOrder.getRetailOrderStatusCode().equals("Paying")) {
                Tracer.writeLine("订单" + outTradeNo + "状态为不对！ ,当前状态编码为：" + theRetailOrder.getRetailOrderStatusCode());
                throw new Exception("订单" + outTradeNo + "状态为不对！ ,当前状态编码为：" + theRetailOrder.getRetailOrderStatusCode());
            }

            theRetailOrder.setRetailOrderStatusCode("Paid");

            // 微信交易号
            theRetailOrder.setWxPayTransactionId(wxPayTransactionId);
            // 支付时间
            theRetailOrder.setPayTime(DateHelper.getCurrentTimestamp());
            this.update(sqlSession, theRetailOrder);


            if (theRetailOrder.getNightStoryDays() != null) {
                //修改会员晚安故事到期时间
                String sql = String.format("UPDATE member\n" +
                        "SET night_story_expire_date = CASE\n" +
                        "    WHEN night_story_expire_date IS NULL OR night_story_expire_date < NOW() THEN DATE_ADD(NOW(), INTERVAL %s DAY)\n" +
                        "    ELSE DATE_ADD(night_story_expire_date, INTERVAL %s DAY)\n" +
                        "END\n" +
                        "WHERE id = %d;", theRetailOrder.getNightStoryDays(), theRetailOrder.getNightStoryDays(), theRetailOrder.getMemberId());
                MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            }
            if (theRetailOrder.getClubActivityId() != null) {
                ClubActivity theClubActivity = ClubActivityManager.getInstance().getEntity(sqlSession, theRetailOrder.getClubActivityId());
                ClubActivityMember newClubActivityMember = new ClubActivityMember();
                newClubActivityMember.setMemberId(theRetailOrder.getMemberId());
                newClubActivityMember.setClubActivityId(theRetailOrder.getClubActivityId());
                newClubActivityMember.setJoinDate(DateHelper.getCurrentTimestamp());
                newClubActivityMember.setSurplusQuantity(theClubActivity.getQuantity());
                newClubActivityMember.setRetailOrderId(theRetailOrder.getId());
                ClubActivityMemberManager.getInstance().add(sqlSession, newClubActivityMember);
                //添加积分记录
                MemberScoreType theMemberScoreType = MemberScoreTypeManager.getInstance().getEntity(sqlSession, "ClubActivity");
                MemberScore newMemberScore = new MemberScore();
                newMemberScore.setMemberId(theRetailOrder.getMemberId());
                newMemberScore.setScore(theMemberScoreType.getScore());
                newMemberScore.setMemberScoreTypeCode("ClubActivity");
                newMemberScore.setScoreTime(DateHelper.getCurrentTimestamp());
                MemberScoreManager.getInstance().add(sqlSession, newMemberScore);
                String sql = String.format("UPDATE member SET score = COALESCE(score, 0) + %d WHERE id=%d",
                        theMemberScoreType.getScore(), theRetailOrder.getMemberId());
                MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            }
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw e;
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

}