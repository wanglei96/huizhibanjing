package BRP.model;

import BRP.data.BookOrder;
import BRP.data.Member;
import BRP.data.MemberBorrowQuantityLog;
import BRP.data.MemberScore;
import BRP.data.MemberScoreType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.ScheduledTask;
import strosoft.app.util.DateHelper;
import strosoft.main.boot.JAppApplication;

import java.io.IOException;
import java.util.List;

public class AddMemberBorrowScoreScheduledTask extends ScheduledTask {
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(JAppApplication.class);

    /**
     * 处理定时任务
     */
    @Override
    protected void process() {
        try {
            //修改会员可借书数量
            this.addMemberBorrowScore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMemberBorrowScore() throws IOException {
        {
            SqlSession session = MyBatisManager.getInstance().openSession();
            try {
                //获取今日借书的会员
                String condition = String.format("DATE(delivered_time) = CURDATE()");
                List<BookOrder> lstBookOrder = BookOrderManager.getInstance().getList(session, condition);
                MemberScoreType theMemberScoreType = MemberScoreTypeManager.getInstance().getEntity(session, "WeeklyBorrowing");
                for (BookOrder bookOrder : lstBookOrder) {
                    //获取添加积分记录
                    String memberScireCondition = String.format(
                            "member_id = %d and member_score_type_code='WeeklyBorrowing' and create_time >= DATE_SUB(NOW(), INTERVAL 1 WEEK) AND create_time <= NOW()",
                            bookOrder.getMemberId());
                    Integer memberScoreCount = MemberScoreManager.getInstance().getCount(session, memberScireCondition);
                    if (memberScoreCount == 0) {
                        //添加会员积分
                        MemberScore memberScore = new MemberScore();
                        memberScore.setMemberId(bookOrder.getMemberId());
                        memberScore.setMemberScoreTypeCode("WeeklyBorrowing");
                        memberScore.setScoreTime(DateHelper.getCurrentTimestamp());
                        memberScore.setScore(theMemberScoreType.getScore());
                        MemberScoreManager.getInstance().add(session, memberScore);
                        //修改会员积分
                        String sql = String.format("UPDATE member SET score = COALESCE(score, 0) + %d WHERE id =%d",
                                theMemberScoreType.getScore(), bookOrder.getMemberId());
                        MyBatisManager.getInstance().executeUpdate(session, sql);
                    }
                }
                MyBatisManager.getInstance().commitSession(session);
            } catch (Exception ex) {
                MyBatisManager.getInstance().rollbackSession(session);
                throw ex;
            } finally {
                MyBatisManager.getInstance().closeSession(session);
            }
        }
    }
}
