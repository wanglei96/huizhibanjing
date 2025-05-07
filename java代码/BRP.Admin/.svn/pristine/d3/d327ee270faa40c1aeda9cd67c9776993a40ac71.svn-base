package strosoft.sys.service;

import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HideIdTableServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        Integer id = JsonHelper.getInt(jData, "id");
        String tableName = JsonHelper.getString(jData, "tableName");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            String sql = String.format("update %s set is_deleted=1 where id = %d", tableName, id);
            MyBatisManager.getInstance().executeUpdate(sqlSession, sql);
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
