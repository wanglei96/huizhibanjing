package BRP.service;

import BRP.data.DeliverPlace;
import BRP.model.DeliverPlaceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class GetSortableServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        JSONArray jsonArray = JsonHelper.getJSONArray(jData,"tableValue");
        int i = 0;
        for(Object obj : jsonArray){
            Map map = (Map)obj;
            int id = Integer.valueOf(map.get("id").toString());
            i++;
            DeliverPlace deliverPlace = DeliverPlaceManager.getInstance().getEntity(id);
            deliverPlace.setDisplayOrder(i);
            DeliverPlaceManager.getInstance().update(deliverPlace);
        }
        this.writeSuccessResponse(response, "排序成功");
    }
}
