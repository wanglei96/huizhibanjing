package BRP.service;

import BRP.data.DeliverPlace;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UpdateDeliverPlaceDisplayOrderServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = getRequestData(request);
       String deliverPlaceIds = JsonHelper.getString(jData, "deliverPlaceIds");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {

            String sql = String.format("SET @rank = 0;\n" +
                    "\n" +
                    "UPDATE deliver_place\n" +
                    "JOIN (\n" +
                    "    SELECT id, (@rank := @rank + 1) AS new_display_order\n" +
                    "    FROM deliver_place\n" +
                    "    WHERE id IN (%s)\n" +
                    "    ORDER BY FIELD(id, %s)\n" +
                    ") AS ranked\n" +
                    "ON deliver_place.id = ranked.id\n" +
                    "SET deliver_place.display_order = ranked.new_display_order;",deliverPlaceIds,deliverPlaceIds);
            MyBatisManager.getInstance().executeUpdate(sqlSession,sql);
            writeSuccessResponse(response);
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new Exception(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
