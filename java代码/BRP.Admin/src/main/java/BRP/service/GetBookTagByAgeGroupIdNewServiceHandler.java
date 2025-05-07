package BRP.service;

import BRP.model.BookTagManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * 获取年龄段下标签
 */
public class GetBookTagByAgeGroupIdNewServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer ageGroupId = JsonHelper.getInt(jData, "ageGroupId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String languageCode =JsonHelper.getString(jData, "languageCode");
        ArrayList<LinkedHashMap<String, Object>>  lhmData = BookTagManager.getInstance().getNewAllBookTagByAgeGroupId(companyId,ageGroupId,languageCode);
        JSONArray result = JsonHelper.toJSONArray(lhmData);
        this.writeSuccessResponse(response, result);
    }
}
