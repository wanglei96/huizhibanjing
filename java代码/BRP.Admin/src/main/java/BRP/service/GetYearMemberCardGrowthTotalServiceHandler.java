package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetYearMemberCardGrowthTotalServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = jData.getInt("companyId");
        String sql = "SELECT \n" +
                "    DATE_FORMAT(date_range.date, '%Y-%m') AS order_month,\n" +
                "    COUNT(orders.effective_date) AS order_count\n" +
                "FROM\n" +
                "    (\n" +
                "        SELECT CURDATE() - INTERVAL n MONTH AS date\n" +
                "        FROM (\n" +
                "            SELECT 0 AS n UNION ALL\n" +
                "            SELECT 1 UNION ALL\n" +
                "            SELECT 2 UNION ALL\n" +
                "            SELECT 3 UNION ALL\n" +
                "            SELECT 4 UNION ALL\n" +
                "            SELECT 5 UNION ALL\n" +
                "            SELECT 6 UNION ALL\n" +
                "            SELECT 7 UNION ALL\n" +
                "            SELECT 8 UNION ALL\n" +
                "            SELECT 9 UNION ALL\n" +
                "            SELECT 10 UNION ALL\n" +
                "            SELECT 11\n" +
                "        ) AS numbers\n" +
                "    ) AS date_range\n" +
                "LEFT JOIN\n" +
                "    (\n" +
                "        SELECT \n" +
                "            DATE(effective_date) AS effective_date\n" +
                "        FROM \n" +
                "            member_card\n" +
                "        WHERE \n" +
                "            effective_date >= CURDATE() - INTERVAL 1 YEAR  and company_id=" + companyId + "\n" +
                "    ) AS orders ON DATE_FORMAT(date_range.date, '%Y-%m') = DATE_FORMAT(orders.effective_date, '%Y-%m')\n" +
                "GROUP BY \n" +
                "    DATE_FORMAT(date_range.date, '%Y-%m')\n" +
                "ORDER BY \n" +
                "    order_month;";
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        JSONArray jResult = JsonHelper.toJSONArray(alData);
        this.writeSuccessResponse(response, jResult);
    }
}
