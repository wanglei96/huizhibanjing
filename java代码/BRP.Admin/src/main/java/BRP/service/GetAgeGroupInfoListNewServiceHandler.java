package BRP.service;

import BRP.model.AgeGroupManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GetAgeGroupInfoListNewServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer companyId = jData.getInt("companyId");
        ArrayList<LinkedHashMap<String, Object>> lhmData = this.getData(companyId);

        JSONArray result = JsonHelper.toJSONArray(lhmData);
        this.writeSuccessResponse(response, result);

    }

    private ArrayList<LinkedHashMap<String, Object>> getData(Integer companyId) throws Exception {
        String sql = String.format("SELECT\n" +
                "      ag.name AS age_group_name,\n" +
                "      ag.id AS age_group_id,\n" +
                "      l.code AS language_code,\n" +
                "      l.name AS language_name,\n" +
                "      group_concat(DISTINCT gt.book_tag_name ORDER BY gt.book_tag_display_order SEPARATOR ', ') AS book_tag_names,\n" +
                "      count( DISTINCT sg.book_sku_id ) AS sku_count,\n" +
                "      count( DISTINCT b.id ) AS book_count \n" +
                "FROM age_group ag\n" +
                "CROSS JOIN language l\n" +
                "LEFT JOIN view_book_sku_age_group sg ON sg.age_group_id = ag.id and sg.company_id = %d and sg.language_code = l.code\n" +
                "LEFT JOIN book b ON sg.book_sku_id = b.book_sku_id \n" +
                "LEFT JOIN view_age_group_book_tag gt ON gt.age_group_id = ag.id and gt.company_id = %d and gt.language_code = l.code\n" +
                "GROUP BY ag.name,ag.id,l.code,l.name\n" +
                "ORDER BY l.code,ag.display_order;", companyId, companyId);
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        return alData;
    }
}
