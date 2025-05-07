package BRP.service;

import BRP.model.BookSkuManager;
import BRP.model.MemberManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMemberByCompanyIdServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String type = JsonHelper.getString(jData, "type");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            if (type.equals("member")) {
                MemberManager.getInstance().deleteByCompanyId(sqlSession, companyId);
            } else if (type.equals("bookSku")) {
                BookSkuManager.getInstance().deleteByCompanyId(sqlSession, companyId);
            }
            writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
