package BRP.service;

import BRP.data.AgeGroupBookTag;
import BRP.model.AgeGroupBookTagManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.Manager;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.context.annotation.ComponentScan;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.SysMenuManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 添加年龄段标签
 */
@ComponentScan
public class AddAgeGroupBookTagByAgeGroupIdServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer ageGroupId = JsonHelper.getInt(jData, "ageGroupId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        List<Integer> lstbookTagIds = JsonHelper.getIntArray(jData, "bookTagIds");
        String languageCode = JsonHelper.getString(jData, "languageCode");
        AgeGroupBookTagManager.getInstance().addAgeGroupBookTagByAgeGroupId(
                ageGroupId, lstbookTagIds,languageCode,companyId);
        this.writeSuccessResponse(response);
    }

}
