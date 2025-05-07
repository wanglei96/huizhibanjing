package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.ClassHelper;
import strosoft.app.util.DateHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberScoreManager extends MemberScoreMaster {
    private static MemberScoreManager _instance;

    private MemberScoreManager() {
        this.setTableName("member_score");
        this.setTypeName("MemberScore");
    }

    public static MemberScoreManager getInstance() {
        if (_instance == null) {
            _instance = new MemberScoreManager();
        }
        return _instance;
    }

    /**
     * 批量添加积分
     *
     * @param memberIds
     * @param score
     * @param memberScoreTypeCode
     */
    public void batchUpdateMemberScore(List<Integer> memberIds, Integer score, String memberScoreTypeCode) throws Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();

        try {
            List<MemberScore> newMemberScoreList = new ArrayList<>();
            for (Integer memberId : memberIds) {
                MemberScore newMemberScore = new MemberScore();
                newMemberScore.setMemberId(memberId);
                newMemberScore.setScore(score);
                newMemberScore.setMemberScoreTypeCode(memberScoreTypeCode);
                newMemberScore.setScoreTime(DateHelper.getCurrentTimestamp());
                newMemberScoreList.add(newMemberScore);
            }
            MemberScoreManager.getInstance().addList(sqlSession, newMemberScoreList);

            //获取逗号分隔的字符串
            String memberIdString = memberIds.stream().map(String::valueOf)
                    .collect(Collectors.joining(","));
            String sql = String.format("UPDATE member SET score = COALESCE(score, 0) + %d WHERE id IN (%s)", score, memberIdString);

            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}