package BRP.service;

import BRP.model.AgeGroupBookTagManager;
import BRP.model.BookSkuManager;
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
public class BatchUpdateBookSkuAgeTagServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        List<Integer> lstBookSkuIds = JsonHelper.getIntArray(jData,"bookSkuIds");
        List<Integer> lstAgeGroupIds = JsonHelper.getIntArray(jData,"ageGroupIds");
        List<Integer> lstBookTagIds = JsonHelper.getIntArray(jData,"bookTagIds");
        BookSkuManager.getInstance().batchUpdateBookSkuAgeTag(lstBookSkuIds, lstAgeGroupIds, lstBookTagIds);
        this.writeSuccessResponse(response);
    }

}
