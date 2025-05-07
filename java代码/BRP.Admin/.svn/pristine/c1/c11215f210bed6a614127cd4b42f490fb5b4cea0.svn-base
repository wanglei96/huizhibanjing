package BRP.service;

import BRP.data.BookTag;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GetViewBookTagListByConditionServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer ageGroupId = JsonHelper.getInt(jData, "ageGroupId");
        String languageCode = JsonHelper.getString(jData, "languageCode");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        ArrayList<LinkedHashMap<String, Object>> lhmData = this.getData(companyId, ageGroupId, languageCode);

       List<BookTag> lstBookTag =  JsonHelper.toJavaList(BookTag.class, lhmData);

        //JSONArray result = JsonHelper.toJSONArray(lhmData);
        this.writeSuccessResponse(response, lstBookTag);
    }

    private ArrayList<LinkedHashMap<String, Object>> getData(Integer companyId, Integer ageGroupId, String languageCode) throws Exception {
        String sql = String.format("SELECT DISTINCT t.*\n" +
                        "FROM book_tag t\n" +
                        "LEFT JOIN age_group_book_tag abt ON t.id = abt.book_tag_id \n" +
                        "WHERE t.is_visible = true %s %s %s \n"+
                        "ORDER BY CASE WHEN t.display_order IS NULL THEN 1 ELSE 0 END, t.display_order ASC, t.create_time DESC",
                companyId != null ? String.format(" and t.company_id = %s", companyId) : "and t.company_id=(SELECT id FROM company where is_default is true) ",
                StringHelper.isNullOrEmpty(languageCode) ? "" : String.format(" and t.language_code = '%s'", languageCode),
                ageGroupId != -1 ? String.format(" and abt.age_group_id = %s", ageGroupId) : ""
        );
        ArrayList<LinkedHashMap<String, Object>> lhmData = MyBatisManager.getInstance().executeHashMapList(sql);
        return lhmData;
    }
}
