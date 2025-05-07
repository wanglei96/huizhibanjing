package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GetBookOutOrderAreaTotalServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Integer deliverAreaId = JsonHelper.getInt(jData, "deliverAreaId");
        Integer deliverLineId = JsonHelper.getInt(jData, "deliverLineId");
        Integer deliverPlaceId = JsonHelper.getInt(jData, "deliverPlaceId");
        Integer delivererId = JsonHelper.getInt(jData, "delivererId");
        Integer bookpickerId = JsonHelper.getInt(jData, "bookpickerId");
        String bookOutOrderStatusCode = JsonHelper.getString(jData, "bookOutOrderStatusCode");
        String deliverTimeBegin = JsonHelper.getString(jData, "deliverTimeBegin");
        String condition = JsonHelper.getString(jData, "condition");
        String deliverTimeEnd = JsonHelper.getString(jData, "deliverTimeEnd");
        MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
        conditionBuilder.add(condition);
        conditionBuilder.add("is_offline=0");
        conditionBuilder.addEqualCondition("company_id", companyId);
        conditionBuilder.addEqualCondition("deliver_area_id", deliverAreaId);
        conditionBuilder.addEqualCondition("deliver_line_id", deliverLineId);
        conditionBuilder.addEqualCondition("deliver_place_id", deliverPlaceId);
        conditionBuilder.addEqualCondition("deliverer_id", delivererId);
        conditionBuilder.addEqualCondition("bookpicker_id", bookpickerId);
        conditionBuilder.addEqualCondition("book_out_order_status_code", bookOutOrderStatusCode);
        conditionBuilder.addTimeSpanCondition(deliverTimeBegin, deliverTimeEnd, "deliver_time");
        String sqlCondition = conditionBuilder.toString();

        String sqlWhere = "";
        if (sqlCondition != null && !sqlCondition.equals("")) {
            sqlWhere = " where " + sqlCondition;
        }
        String sql = String.format("SELECT  deliver_area_id, deliver_area_name, COUNT(1) AS count\n" +
                "FROM (\n" +
                "    SELECT book_order_id, deliver_area_id, deliver_area_name\n" +
                "    FROM view_book_out_order %s\n" +
                "    GROUP BY book_order_id, deliver_area_id, deliver_area_name\n" +
                ") AS sub_query\n" +
                "GROUP BY  deliver_area_id, deliver_area_name", sqlWhere);
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);

        String notSql = String.format("SELECT \n" +
                "    SUM(count_not_found) AS total_not_found_count\n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        COUNT(DISTINCT CASE WHEN book_out_order_status_code = 'NotFound' THEN deliver_area_id END) AS count_not_found\n" +
                "    FROM \n" +
                "        view_book_out_order\n" +
                "   %s\n" +
                "    GROUP BY \n" +
                "        book_order_id\n" +
                ") AS counts_per_order;",sqlWhere+" AND book_out_order_status_code = 'NotFound'");
        Integer notFoundCount =MyBatisManager.getInstance().executeInt(notSql);
        JSONObject jDataResult = new JSONObject();
        JSONArray alDataJson = new JSONArray();
        int totalCount = 0;
        for (LinkedHashMap<String, Object> lhmData : alData) {
            JSONObject jDataItem = new JSONObject();
            jDataItem.put("deliverAreaId", lhmData.get("deliver_area_id"));
            jDataItem.put("deliverAreaName", lhmData.get("deliver_area_name"));
            jDataItem.put("count", lhmData.get("count"));
            totalCount += (Long) lhmData.get("count");
            alDataJson.add(jDataItem);
        }
        jDataResult.put("areaList", alDataJson);
        jDataResult.put("totalCount",totalCount);
        jDataResult.put("notFoundCount",notFoundCount);
        this.writeSuccessResponse(response, jDataResult);
    }
}
