package BRP.service;

import BRP.model.TimeTypes;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
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
 * 取得平台端门店统计
 */
public class GetPlatformCompanyTotalServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        String timeType = JsonHelper.getString(jData, "timeType");
        String sql = "";
        if (timeType.equals(TimeTypes.Month)) {
            sql = "SELECT CONCAT(year(create_time), '-', month(create_time)) as time ,count(*) as company_count\n" +
                    "from company\n" +
                    "group by CONCAT(year(create_time), '-', month(create_time)) ";
        } else if (timeType.equals(TimeTypes.Year)) {
            sql = "select year(create_time) as time ,count(*) as company_count\n" +
                    "from company\n" +
                    "group by year(create_time)";
        }
        ArrayList<LinkedHashMap<String, Object>> lhmData = MyBatisManager.getInstance().executeHashMapList(sql);
        this.writeSuccessResponse(response, lhmData);
    }
}
