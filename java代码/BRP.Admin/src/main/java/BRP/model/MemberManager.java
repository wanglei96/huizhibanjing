package BRP.model;

import BRP.data.Company;
import BRP.data.Member;
import BRP.data.MemberMaster;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.StringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MemberManager extends MemberMaster {
    private static MemberManager _instance;

    private MemberManager() {
        this.setTableName("member");
        this.setTypeName("Member");
    }

    public static MemberManager getInstance() {
        if (_instance == null) {
            _instance = new MemberManager();
        }
        return _instance;
    }

    public Member registerOrLoginMember(String wxaOpenId, Integer companyId, String mobilePhone, String unionId) throws IOException {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();

        try {
            String sql = String.format("update member set wxa_open_id = null where wxa_open_id = '%s'", wxaOpenId);
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            String sql1 = String.format("update member set union_id = null where union_id = '%s'", unionId);
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql1);
            Member theMember = null;
            if (mobilePhone != null && !mobilePhone.isEmpty()) {
                theMember = MemberManager.getInstance().getEntityByMobilePhone(sqlSession, mobilePhone);
            }
            if (theMember == null && wxaOpenId != null && !wxaOpenId.isEmpty()) {
                theMember = MemberManager.getInstance().getEntityByWxaOpenId(sqlSession, wxaOpenId);
            }

            //查询到会员则返回会员 ， 没查询到则创建会员
            if (theMember != null) {
                if (mobilePhone != null && StringHelper.isNullOrEmpty(theMember.getMobilePhone())) {
                    theMember.setMobilePhone(mobilePhone);
                }
                if (wxaOpenId != null) {
                    theMember.setWxaOpenId(wxaOpenId);
                }
                if (unionId != null) {
                    theMember.setUnionId(unionId);
                }
                MemberManager.getInstance().update(sqlSession, theMember);
                MyBatisManager.getInstance().commitSession(sqlSession);
                return theMember;
            }

            Member newMember = new Member();

            if (companyId != null) {
                newMember.setCompanyId(companyId);
            } else {
                newMember.setCompanyId(this.getDefaultCompanyId(sqlSession));
            }
            newMember.setWxaOpenId(wxaOpenId);
            newMember.setName("微信用户");
            newMember.setMobilePhone(mobilePhone);
            newMember.setUnionId(unionId);
            String memberNo = MyBatisManager.getInstance().createRandomNo(sqlSession, "member", "member_no", 8);
            newMember.setMemberNo(memberNo);

            int id = this.add(sqlSession, newMember);
            newMember.setId(id);
            MyBatisManager.getInstance().commitSession(sqlSession);
            return newMember;
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

    private Integer getDefaultCompanyId(SqlSession sqlSession) throws Exception {
        List<Company> companyList = CompanyManager.getInstance().getList(sqlSession, "is_default is true");
        if (companyList.size() > 0) {
            return companyList.get(0).getId();
        } else {
            throw new Exception("未查询到默认商户");
        }
    }

    public ArrayList<LinkedHashMap<String, Object>> getMemberBorrowBookTotal(Integer companyId, String cardNo, String memberName, String mobilePhone, String createTimeBegin, String createTimeEnd, String orderBy, Integer pageIndex, Integer pageSize) throws Exception {
        String sqlLimit = "";
        if (pageSize > 0) {
            sqlLimit = " limit " + String.valueOf(pageIndex * pageSize) + "," + pageSize;
        }
        String sql = String.format("SELECT\n" +
                        "    mc.id,mc.member_id,mc.company_id,mc.member_name,mc.mobile_phone,mc.card_no,\n" +
                        "\t\tmc.effective_date,mc.expire_date,mc.batch_name,\n" +
                        "    COUNT(boi.id) AS total_borrow_count,\n" +
                        "COUNT(DISTINCT boi.book_order_id) AS order_borrow_count,\n" +
                        "    ifnull(SUM(boi.book_sku_price),0) AS total_price\n" +
                        "FROM\n" +
                        "    view_member_card mc\n" +
                        "LEFT JOIN\n" +
                        "    view_book_order_item boi ON mc.member_id = boi.member_id AND boi.book_order_type_code = 'Borrow' %s \n" +
                        "WHERE\n" +
                        "    mc.disabled IS NOT TRUE AND mc.member_id IS NOT NULL AND mc.company_id=%d %s %s %s\n" +
                        "GROUP BY\n" +
                        "    mc.id,mc.member_id,mc.company_id,mc.member_name,mc.mobile_phone,mc.card_no,\n" +
                        "\t\tmc.effective_date,mc.expire_date,mc.batch_name ORDER BY %s  %s",
                (!StringHelper.isNullOrEmpty(createTimeBegin) && !StringHelper.isNullOrEmpty(createTimeEnd))
                        ? "AND boi.create_time BETWEEN '" + createTimeBegin + "' AND '" + createTimeEnd + "'"
                        : "", companyId,
                (!StringHelper.isNullOrEmpty(cardNo)) ? "AND mc.card_no LIKE '%" + cardNo + "%'" : "",
                (!StringHelper.isNullOrEmpty(memberName)) ? "AND mc.member_name LIKE '%" + memberName + "%'" : "",
                (!StringHelper.isNullOrEmpty(mobilePhone)) ? "AND mc.mobile_phone LIKE '%" + mobilePhone + "%'" : "",
                orderBy, sqlLimit);
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        return alData;
    }

    public ArrayList<LinkedHashMap<String, Object>> getMemberScoreRanking(
            Integer companyId, String cardNo, String memberName, String createTimeBegin, String createTimeEnd, String orderBy, Integer pageIndex, Integer pageSize) throws Exception {
        String sqlLimit = "";
        if (pageSize > 0) {
            sqlLimit = " limit " + String.valueOf(pageIndex * pageSize) + "," + pageSize;
        }
        String sql = String.format("SELECT \n" +
                        "    m.id, \n" +
                        "    m.name, \n" +
                        "    m.company_id as company_id, \n" +
                        "    m.score as score, \n" +
                        "    mc.card_no as card_no,\n" +
                        "    IFNULL(sum_negative.score, 0) AS expend_score,\n" +
                        "    IFNULL(sum_positive.score, 0) AS add_score\n" +
                        "FROM \n" +
                        "    member m\n" +
                        "LEFT JOIN \n" +
                        "    (SELECT member_id, IFNULL(SUM(score), 0) AS score\n" +
                        "     FROM member_score\n" +
                        "     WHERE score < 0 %s\n" +
                        "     GROUP BY member_id) AS sum_negative\n" +
                        "ON sum_negative.member_id = m.id\n" +
                        "LEFT JOIN \n" +
                        "    (SELECT member_id, IFNULL(SUM(score), 0) AS score\n" +
                        "     FROM member_score\n" +
                        "     WHERE score > 0 %s\n" +
                        "     GROUP BY member_id) AS sum_positive\n" +
                        "ON sum_positive.member_id = m.id\n" +
                        "LEFT JOIN member_card mc ON m.id = mc.member_id AND  mc.disabled IS NOT TRUE\n" +
                        "where m.company_id = %d and mc.card_no is not null %s %s\n" +
                        "ORDER BY %s  %s",
                (!StringHelper.isNullOrEmpty(createTimeBegin) && !StringHelper.isNullOrEmpty(createTimeEnd))
                        ? "AND create_time BETWEEN '" + createTimeBegin + "' AND '" + createTimeEnd + "'"
                        : "",
                (!StringHelper.isNullOrEmpty(createTimeBegin) && !StringHelper.isNullOrEmpty(createTimeEnd))
                ? "AND create_time BETWEEN '" + createTimeBegin + "' AND '" + createTimeEnd + "'"
                : "",
                companyId,
                (!StringHelper.isNullOrEmpty(cardNo)) ? "AND mc.card_no LIKE '%" + cardNo + "%'" : "",
                (!StringHelper.isNullOrEmpty(memberName)) ? "AND m.name LIKE '%" + memberName + "%'" : "",
                orderBy, sqlLimit);
        return MyBatisManager.getInstance().executeHashMapList(sql);
    }
}