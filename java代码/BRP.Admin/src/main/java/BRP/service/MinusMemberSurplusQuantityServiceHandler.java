package BRP.service;

import BRP.data.ClubActivityMember;
import BRP.model.ClubActivityMemberManager;
import BRP.model.MemberManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MinusMemberSurplusQuantityServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
        JSONObject requestData = getRequestData(request);
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();

        try {
            Integer id = JsonHelper.getInt(requestData, "id");
            ClubActivityMember clubActivityMember = ClubActivityMemberManager.getInstance().getEntity(sqlSession, id);
            if (clubActivityMember.getSurplusQuantity() >= 1) {
                clubActivityMember.setSurplusQuantity(clubActivityMember.getSurplusQuantity() - 1);
                ClubActivityMemberManager.getInstance().update(sqlSession, clubActivityMember);
            } else {
                throw new Exception("次数不足");
            }
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
        writeSuccessResponse(response);
    }
}
