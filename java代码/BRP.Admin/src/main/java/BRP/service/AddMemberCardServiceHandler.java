package BRP.service;

import BRP.data.Company;
import BRP.data.MemberCard;
import BRP.data.MemberCardBatch;
import BRP.model.CompanyManager;
import BRP.model.MemberCardBatchManager;
import BRP.model.MemberCardManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.CollectionUtils;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.NumberStringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 生成会员卡
 */
public class AddMemberCardServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer num = JsonHelper.getInt(jData, "num");
        Integer companyId = jData.getInt("companyId");
        Integer memberCardBatchId = jData.getInt("memberCardBatchId");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            if (num > 999) {
                this.writeErrorResponse(response, "会员卡生成上限为999个");
                return;
            }
            // 获取当前门店的门店编号
            Company company = CompanyManager.getInstance().getEntity(sqlSession, companyId);
            // 获取当前批次的批次编号
            MemberCardBatch memberCardBatch = MemberCardBatchManager.getInstance().getEntity(sqlSession, memberCardBatchId);
            // 获取会员卡仓库总条数

            //Long newNo = generateBookSn(sqlSession, company.getCompanyNo(), memberCardBatch.getBatchNo());
            for (int i = 0; i < num; i++) {
                // 获取最大会员卡号 + " and member_card_batch_id = " + memberCardBatch.getBatchNo()
                //List<MemberCard> memberCardList = MemberCardManager.getInstance().getList(sqlSession, "company_id = " + companyId + " order by no desc");
                MemberCard memberCard = new MemberCard();
                memberCard.setCompanyId(companyId);
                memberCard.setMemberCardBatchId(memberCardBatchId);
                Map<String, String> map = customNo(sqlSession, company, memberCardBatch.getBatchNo());
                memberCard.setCardNo(map.get("cardNo"));
                memberCard.setNo(map.get("no"));
                memberCard.setCreateTime(new Timestamp(System.currentTimeMillis()));
                memberCard.setDisabled(false); // 生成的会员卡默认禁用
                memberCard.setIsSpecial(false); // 默认非特殊会员
                memberCard.setStoppableCardQuantity(3); // 默认可停卡次数
                memberCard.setBorrowableQuantity(0); // 默认可借图书数量
                MemberCardManager.getInstance().add(sqlSession, memberCard);
            }
            this.writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }

    private Long generateBookSn(SqlSession sqlSession, String companyNo, String batchNo) {
        String condition = String.format("card_no LIKE '%%s%'", companyNo + batchNo);
        List<MemberCard> lstMemberCard = MemberCardManager.getInstance().getList(sqlSession, condition, "card_no desc");
        if (lstMemberCard != null && lstMemberCard.size() > 0) {
            String no = recursionCardNo(lstMemberCard.get(0).getCardNo());
            return Long.parseLong(no);
        }
        //long longBookSn = Long.parseLong(bookNo + String.format("%04d", 1));
        return Long.parseLong(companyNo + batchNo + "001");
    }

    /**
     * 自定义批次编号
     */

    public static Map<String, String> customNo(SqlSession sqlSession, Company company, String batchNo) {
        Map<String, String> map = new HashMap<>();
        String condition = String.format("company_id=%d and card_no LIKE '%s%%'", company.getId(), company.getCompanyNo() + batchNo);
        List<MemberCard> lstMemberCard = MemberCardManager.getInstance().getList(sqlSession, condition, "card_no desc");
        if (lstMemberCard == null || lstMemberCard.size() == 0) {
            map.put("no", "001");
            map.put("cardNo", String.format("%s%s%s", company.getCompanyNo(), batchNo, "001"));
            return map;
        }
        String no = recursionCardNo(lstMemberCard.get(0).getCardNo());
        //String cardNo = String.format("%s%s%s", no);
        map.put("no", no);
        map.put("cardNo", no);
        return map;
    }

    /**
     * 卡号递归，不包含4
     *
     * @return
     */
    public static String recursionCardNo(String no) {
        no = NumberStringHelper.numberStringSum(no, "1");
        if (no.contains("4")) {
            return recursionCardNo(no);
        }
        return no;
    }
}