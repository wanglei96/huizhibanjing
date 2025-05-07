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

public class GetMemberAgeGroupTotalServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String companyId = jData.getString("companyId");
        String companyIdCondition = "";
        if(companyId != "null"){
            companyIdCondition = "and mc.company_id=" + companyId;
        }
        String sql = "SELECT age_ranges.age_range AS name, COUNT(member_age.age_range) AS value\n" +
                "FROM (\n" +
                "    SELECT '0-2岁' AS age_range\n" +
                "    UNION ALL\n" +
                "    SELECT '3-6岁'\n" +
                "    UNION ALL\n" +
                "    SELECT '7-10岁'\n" +
                "    UNION ALL\n" +
                "    SELECT '11-14岁'\n" +
                "    UNION ALL\n" +
                "    SELECT '其他'\n" +
                ") AS age_ranges\n" +
                "LEFT JOIN (\n" +
                "    SELECT\n" +
                "        CASE\n" +
                "            WHEN TIMESTAMPDIFF(YEAR, m.birthday, CURDATE()) BETWEEN 0 AND 2 THEN '0-2岁'\n" +
                "            WHEN TIMESTAMPDIFF(YEAR, m.birthday, CURDATE()) BETWEEN 3 AND 6 THEN '3-6岁'\n" +
                "            WHEN TIMESTAMPDIFF(YEAR, m.birthday, CURDATE()) BETWEEN 7 AND 10 THEN '7-10岁'\n" +
                "            WHEN TIMESTAMPDIFF(YEAR, m.birthday, CURDATE()) BETWEEN 11 AND 14 THEN '11-14岁'\n" +
                "            ELSE '未知'\n" +
                "        END AS age_range\n" +
                "    FROM\n" +
                "        member_card AS mc\n" +
                "    LEFT JOIN\n" +
                "        member  AS m ON mc.member_id = m.id\n" +
                "    WHERE\n" +
                "        mc.disabled IS NOT TRUE and mc.member_id is not null " + companyIdCondition + "\n" +
                ") AS member_age ON age_ranges.age_range = member_age.age_range\n" +
                "GROUP BY age_ranges.age_range \n" +
                "ORDER BY\n" +
                "    CASE age_ranges.age_range\n" +
                "        WHEN '0-2岁' THEN 1\n" +
                "        WHEN '3-6岁' THEN 2\n" +
                "        WHEN '7-10岁' THEN 3\n" +
                "\t\t\t\tWHEN '11-14岁' THEN 4\n" +
                "        WHEN '其他' THEN 5\n" +
                "        ELSE 6\n" +
                "    END;";
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        JSONArray jResult = JsonHelper.toJSONArray(alData);
        this.writeSuccessResponse(response, jResult);
    }
}
