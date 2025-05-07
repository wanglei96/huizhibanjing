package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetBookInfoListTotalServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = jData.getInt("companyId");
        String sql = "SELECT \n" +
                "count(1) AS book_count,\n" +
                "SUM(CASE WHEN book_status_code = 'On' THEN 1 ELSE 0 END) AS book_on_count,\n" +
                "SUM(CASE WHEN book_status_code = 'Off' THEN 1 ELSE 0 END) AS book_off_count,\n" +
                "SUM(CASE WHEN borrow_status_code != 'In' THEN 1 ELSE 0 END) AS book_borrow_count,\n" +
                "SUM(CASE WHEN is_available is true THEN 1 ELSE 0 END) AS book_available_count,\n" +
                "SUM(CASE WHEN is_damaged is true THEN 1 ELSE 0 END) AS book_damaged_count\n" +
                "FROM book\n" +
                "WHERE company_id = " + companyId;
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
            case "book_count":
                return "图书总册数";
            case "book_on_count":
                return "上架总册数";
            case "book_off_count":
                return "下架总册数";
            case "book_borrow_count":
                return "借出总册数";
            case "book_available_count":
                return "可借总册数";
            case "book_damaged_count":
                return "折损总册数";
            default:
                return "";
        }
    }
}
