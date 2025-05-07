package BRP.service;

import BRP.model.BookTagManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.SysMenuManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/*
 * 取得用户菜单列表
 */
public class GetBookTagByAgeGroupIdServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer ageGroupId = JsonHelper.getInt(jData, "ageGroupId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String languageCode =JsonHelper.getString(jData, "languageCode");
        ArrayList<LinkedHashMap<String, Object>>  lhmData = BookTagManager.getInstance().getAllBookTagByAgeGroupId(companyId,ageGroupId,languageCode);
        JSONArray result = JsonHelper.toJSONArray(lhmData);
        this.writeSuccessResponse(response, result);
    }
}
