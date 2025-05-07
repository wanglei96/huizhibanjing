package BRP.service;

import BRP.data.ViewBookTag;
import BRP.model.BookTagManager;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GetViewBookTagListByConditionOldServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer ageGroupId = JsonHelper.getInt(jData, "ageGroupId");
        String languageCode = JsonHelper.getString(jData, "languageCode");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        ArrayList<LinkedHashMap<String, Object>> lhmData = BookTagManager.getInstance().getBookTagByAgeGroupId(companyId, ageGroupId, languageCode);

       List<ViewBookTag> lstViewBookTag =  JsonHelper.toJavaList(ViewBookTag.class, lhmData);

        //JSONArray result = JsonHelper.toJSONArray(lhmData);
        this.writeSuccessResponse(response, lstViewBookTag);
    }
}
