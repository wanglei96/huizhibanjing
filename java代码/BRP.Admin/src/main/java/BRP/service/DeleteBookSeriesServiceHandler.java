package BRP.service;

import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 删除系列
 */
public class DeleteBookSeriesServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
        Integer id = JsonHelper.getInt(jData, "id");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            String sql = "update book_sku set book_series_id = null where book_series_id = " + id;
            MyBatisManager.getInstance().executeUpdate(sqlSession,sql);
            String sql2 = "delete from book_series where id = " + id;
            MyBatisManager.getInstance().executeDelete(sqlSession,sql2);
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
