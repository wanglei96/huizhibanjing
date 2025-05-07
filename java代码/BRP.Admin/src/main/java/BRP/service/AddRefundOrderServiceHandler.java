package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddRefundOrderServiceHandler extends AddServiceHandler {
    /**
     * 增加后处理函数
     *
     * @param newAddServiceContext
     */
    @Override
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        HttpServletRequest request = newAddServiceContext.getRequest();
        JSONObject jData = this.getRequestData(request);
        String refundTypeCode = JsonHelper.getString(jData, "refundTypeCode");
        Boolean isDamageBook = JsonHelper.getBoolean(jData, "isDamageBook");
        Boolean isCancelMember = JsonHelper.getBoolean(jData, "isCancelMember");
        Boolean isCancelBookbox = JsonHelper.getBoolean(jData, "isCancelBookbox");
        Integer bookId = JsonHelper.getInt(jData, "bookId");
        Integer memberId = JsonHelper.getInt(jData, "memberId");
        Integer payOrderId = JsonHelper.getInt(jData, "payOrderId");
        SqlSession sqlSession = newAddServiceContext.getSession();
        if (refundTypeCode.equals("Damage") && bookId != null) {
            if (isDamageBook != null && isDamageBook) {
                Book theBook = BookManager.getInstance().getEntity(sqlSession, bookId);
                theBook.setIsDamaged(false);
                BookManager.getInstance().update(sqlSession, theBook);
            }
        }
        if (payOrderId != null) {
            String sql = "update pay_order set is_refund=1 where id=" + payOrderId;
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
        }
        if (refundTypeCode.equals("Member") && memberId != null) {
            if (isCancelMember != null && isCancelMember) {
                MemberCard theMemberCard = MemberCardManager.getInstance().getEntityByMemberId(sqlSession, memberId);
                theMemberCard.setMemberCardStatusCode("Cancelled");
                MemberCardManager.getInstance().update(sqlSession, theMemberCard);
            }
            if (isCancelBookbox != null && isCancelBookbox) {
                Member theMember = MemberManager.getInstance().getEntity(sqlSession, memberId);
                theMember.setIsActivated(false);
                theMember.setMemberBookboxStatusCode("Cancel");
                MemberManager.getInstance().update(sqlSession, theMember);
            }
        }
    }
}
