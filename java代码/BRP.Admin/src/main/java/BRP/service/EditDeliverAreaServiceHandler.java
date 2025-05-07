package BRP.service;

import BRP.data.DeliverArea;
import BRP.model.DeliverAreaManager;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 配送大区编辑
 */
public class EditDeliverAreaServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer id = JsonHelper.getInt(jData,"id");
        Integer companyId = JsonHelper.getInt(jData,"companyId");
        String name = JsonHelper.getString(jData,"name");
        String description = JsonHelper.getString(jData,"description");
        DeliverArea deliverArea = new DeliverArea();
        deliverArea.setId(id);
        deliverArea.setCompanyId(companyId);
        deliverArea.setName(name);
        deliverArea.setDescription(description);
        // 判断新建的大区是否已经存在
        if(!StringUtils.isEmpty(name) && id == null){
            int i = DeliverAreaManager.getInstance().getList("company_id = "+ companyId +" and name = '" + name + "'").size();
            if(i > 0){
                this.writeErrorResponse(response,"该配送大区已存在");
                return;
            }
        }
        if(id == null){
            deliverArea.setCreateTime(DateHelper.toTimestamp(new Date()));
            DeliverAreaManager.getInstance().add(deliverArea);
            this.writeSuccessResponse(response, "配送大区创建成功");
        }else{
            DeliverArea deliverAreas = DeliverAreaManager.getInstance().getEntity(id);
            deliverAreas.setCompanyId(companyId);
            deliverAreas.setName(name);
            deliverAreas.setDescription(description);
            deliverAreas.setUpdateTime(DateHelper.toTimestamp(new Date()));
            DeliverAreaManager.getInstance().update(deliverAreas);
            this.writeSuccessResponse(response, "配送大区修改成功");
        }
    }
}
