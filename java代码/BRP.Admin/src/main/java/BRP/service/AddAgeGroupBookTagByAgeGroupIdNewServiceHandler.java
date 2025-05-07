package BRP.service;

import BRP.model.AgeGroupBookTagManager;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 添加年龄段标签
 */
@ComponentScan
public class AddAgeGroupBookTagByAgeGroupIdNewServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer ageGroupId = JsonHelper.getInt(jData, "ageGroupId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        List<Integer> lstbookTagIds = JsonHelper.getIntArray(jData, "bookTagIds");
        String languageCode = JsonHelper.getString(jData, "languageCode");
        AgeGroupBookTagManager.getInstance().addAgeGroupBookTagByAgeGroupIdNew(
                ageGroupId, lstbookTagIds,languageCode,companyId);
        this.writeSuccessResponse(response);
    }

}
