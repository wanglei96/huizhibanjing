package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetMemberInfoListTotalServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = jData.getInt("companyId");
        String sql = "select count(id) AS member_total,\n" +
                "\t\t\t (COUNT(id) - SUM(CASE WHEN expire_date < CURRENT_DATE() OR expire_date IS NULL THEN 1 ELSE 0 END)) AS validity,\n" +
                "\t\t\t SUM(CASE WHEN expire_date < CURRENT_DATE() OR expire_date is null THEN 1 ELSE 0 END) AS expired,\n" +
                "\t\t\t SUM(CASE WHEN expire_date <= CURRENT_DATE() + INTERVAL 90 DAY AND expire_date > CURRENT_DATE() THEN 1 ELSE 0 END) AS expiring_ninety_day_count,\n" +
                "\t\t\t SUM(CASE WHEN expire_date <= CURRENT_DATE() + INTERVAL 30 DAY AND expire_date > CURRENT_DATE() THEN 1 ELSE 0 END) AS expiring_thirty_day_count\n" +
                "from member_card \n" +
                "where member_id is not null and disabled is not true and company_id =" + companyId;
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        LinkedHashMap<String, Object> map = alData.get(0);
        JSONArray jResult = new JSONArray();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String name = this.getNameByKey(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", value);
            jsonObject.put("name", name);
            System.out.println("Key: " + key + ", Value: " + value);
            jResult.add(jsonObject);
        }
        this.writeSuccessResponse(response, jResult);
    }

    private String getNameByKey(String key) {
        switch (key) {
            case "member_total":
                return "会员总数";
            case "validity":
                return "有效会员数";
            case "expired":
                return "到期会员数";
            case "expiring_ninety_day_count":
                return "即将到期数（90天内）";
            case "expiring_thirty_day_count":
                return "即将到期数（30天内）";
            default:
                return "";
        }
    }
}
