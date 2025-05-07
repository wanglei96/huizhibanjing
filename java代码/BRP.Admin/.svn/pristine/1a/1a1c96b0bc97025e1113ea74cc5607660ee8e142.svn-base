package BRP.service;

import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateRandomNoServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        String tableName = JsonHelper.getString(jData, "tableName");
        String filedName = JsonHelper.getString(jData, "filedName");
        Integer length = JsonHelper.getInt(jData, "length");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            String randomNo = MyBatisManager.getInstance().createRandomNo(sqlSession, tableName, filedName, length);
            writeSuccessResponse(response, randomNo);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
