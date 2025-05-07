package BRP.service;

import BRP.data.Member;
import BRP.model.MemberManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.Tracer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMemberWXOpenIDServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        String mobilePhone = JsonHelper.getString(jData, "mobilePhone");
        String WXOpenID = JsonHelper.getString(jData, "WXOpenID");
        Tracer.writeLine("WXOpenID: " + WXOpenID);
        String unionID = JsonHelper.getString(jData, "unionID");
        Tracer.writeLine("unionID: " + unionID);
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            //Member theMember = MemberManager.getInstance().getEntityByMobilePhone(sqlSession, mobilePhone);
            /*if (theMember != null) {
                theMember.setWxOpenId(WXOpenID);
                MemberManager.getInstance().update(sqlSession, theMember);
            }*/
            String sql1 = String.format("update member set wx_open_id = null where union_id = '%s'", unionID);
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql1);
            String sql = String.format("update member set wx_open_id='%s' where union_id='%s'",
                    WXOpenID, unionID);
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
            writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            Tracer.writeLine("异常: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
