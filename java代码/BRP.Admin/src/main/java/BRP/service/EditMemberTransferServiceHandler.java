package BRP.service;

import BRP.data.DeliveryOrderInfo;
import BRP.data.Member;
import BRP.model.MemberManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.CollectionUtils;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class EditMemberTransferServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer oldDeliverPlaceId = JsonHelper.getInt(jData, "oldDeliverPlaceId");
        Integer delivererId = JsonHelper.getInt(jData, "delivererId");
        Integer newDeliverPlaceId = JsonHelper.getInt(jData, "newDeliverPlaceId");

        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            // 获取当前配送点下面的会员
            List<Member> memberList = MemberManager.getInstance().getList(sqlSession, "deliver_place_id = " + oldDeliverPlaceId);
            if (memberList == null || memberList.size() == 0) {
                this.writeErrorResponse(response, "当前配送点下没有会员");
                return;
            }
            String idString = memberList.stream()
                    .map(Member::getId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            String sql = "update member set deliver_place_id = " + newDeliverPlaceId + ",deliverer_id = " + delivererId + " where id in (" + idString + ")";
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            //修改会员配送路线
            String sql2 = "UPDATE member \n" +
                    "SET deliver_line_id = (SELECT deliver_line_id FROM deliver_place WHERE deliver_place.id = member.deliver_place_id) where id in (" + idString + ")";
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql2);
            //修改会员配送大区
            String sql3 = "UPDATE member \n" +
                    "SET deliver_area_id = (SELECT deliver_area_id FROM deliver_line WHERE deliver_line.id = member.deliver_line_id) where id in (" + idString + ")";
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql3);
            this.writeSuccessResponse(response, "转移成功");
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
    }
}
