package BRP.service;

import BRP.model.AgeGroupManager;
import BRP.model.BookTagManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GetAgeGroupInfoListServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer companyId = jData.getInt("companyId");
        ArrayList<LinkedHashMap<String, Object>> lhmData = AgeGroupManager.getInstance().getAgeGroupInfo(companyId);

        JSONArray result = JsonHelper.toJSONArray(lhmData);
        this.writeSuccessResponse(response, result);

    }
}
