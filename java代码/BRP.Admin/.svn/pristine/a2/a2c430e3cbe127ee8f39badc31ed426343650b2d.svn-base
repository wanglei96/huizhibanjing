package strosoft.sys.service;

import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HideCodeTreeTableServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        String code = JsonHelper.getString(jData, "code");
        String tableName = JsonHelper.getString(jData, "tableName");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
            conditionBuilder.addCodeTreeCondition("code", tableName, code);
            String condition = conditionBuilder.toString();
            String sql = String.format("update %s set is_deleted=1 where %s", tableName, condition);
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
