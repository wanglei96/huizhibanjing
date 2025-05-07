package BRP.model;

import BRP.data.*;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberCardManager extends MemberCardMaster {
    private static MemberCardManager _instance;

    private MemberCardManager() {
        this.setTableName("member_card");
        this.setTypeName("MemberCard");
    }

    public static MemberCardManager getInstance() {
        if (_instance == null) {
            _instance = new MemberCardManager();
        }
        return _instance;
    }

    /**
     * 批量增加曾加会员卡失效日期
     *
     * @param sqlSession
     * @param memberCardIdList
     * @param days
     * @throws IOException
     */
    public void delayExpireDate(SqlSession sqlSession, List<String> memberCardIdList, Integer days) throws IOException {
        String idArrStr = String.join(",", memberCardIdList);
        String sql = "UPDATE member_card\n" +
                "SET expire_date = DATE_ADD(expire_date, INTERVAL " + days + " DAY)\n" +
                "WHERE id in (" + idArrStr + ")";
        MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
    }

    /**
     * 根据编号获取会员卡
     *
     * @param session
     * @param memberCardNo
     * @return
     */
    public MemberCard getEntityByMemberCardNo(SqlSession session, Integer companyId, String memberCardNo) {
        String condition = String.format("card_no='%s' and company_id=%d", memberCardNo, companyId);
        List<MemberCard> lstMemberCard = this.getList(session, condition);
        if (lstMemberCard != null && lstMemberCard.size() > 0) {
            return lstMemberCard.get(0);
        }
        return null;
    }

    public void addMemberBorrowTime(Integer companyId, String newMemberCardNo, String oldMemberCardNo) throws Exception {
        SqlSession session = MyBatisManager.getInstance().openSession();
        try {
            MemberCard theNewMemberCard = MemberCardManager.getInstance().getEntityByMemberCardNo(session, companyId, newMemberCardNo);
            if (theNewMemberCard == null) {
                throw new Exception("请输入正确卡号！新会员卡号不存在");
            }
            MemberCard theOldMemberCard = MemberCardManager.getInstance().getEntityByMemberCardNo(session, companyId, oldMemberCardNo);
            if (theOldMemberCard == null) {
                throw new Exception("请输入正确卡号！推荐人卡号不存在");
            }


            List<ActivityServiceTime> newActivityServiceTimelist = new ArrayList<>();

            ActivityServiceTime newNewServiceTime = new ActivityServiceTime();
            newNewServiceTime.setMemberCardId(theNewMemberCard.getId());
            newNewServiceTime.setDays(30);
            newNewServiceTime.setCompanyId(companyId);

            ActivityServiceTime newOldServiceTime = new ActivityServiceTime();
            newNewServiceTime.setMemberCardId(theOldMemberCard.getId());
            newNewServiceTime.setDays(30);
            newNewServiceTime.setCompanyId(companyId);

            newActivityServiceTimelist.add(newNewServiceTime);
            newActivityServiceTimelist.add(newOldServiceTime);
            ActivityServiceTimeManager.getInstance().addList(session, newActivityServiceTimelist);
            //增加会员时长
            List<String> memberCardIdList = new ArrayList<>();
            memberCardIdList.add(theNewMemberCard.getId().toString());
            memberCardIdList.add(theOldMemberCard.getId().toString());
            this.delayExpireDate(session, memberCardIdList, 30);
            MyBatisManager.getInstance().commitSession(session);
        } catch (Exception ex) {
            MyBatisManager.getInstance().rollbackSession(session);
            throw ex;
        } finally {
            MyBatisManager.getInstance().closeSession(session);
        }
    }

    /**
     * 根据会员ID获取会员卡
     *
     * @param session
     * @param memberId
     * @return
     */
    public  MemberCard getEntityByMemberId(SqlSession session, Integer memberId){
        String condition = String.format("member_id=%d and disabled is not true", memberId);
        List<MemberCard> lstMemberCard = this.getList(session, condition);
        if(lstMemberCard != null && lstMemberCard.size() > 0){
            return lstMemberCard.get(0);
        }
        return null;
    }
}