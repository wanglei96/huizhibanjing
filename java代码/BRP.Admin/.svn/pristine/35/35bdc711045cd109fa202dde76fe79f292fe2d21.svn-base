package BRP.service;

import BRP.data.DeliverPlace;
import BRP.model.DeliverPlaceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class GetDeliverPlaceSortServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);

        Integer delivererId = JsonHelper.getInt(jData, "delivererId");
        JSONArray jsonArray = JsonHelper.getJSONArray(jData,"tableValue");


        if(jsonArray != null && !jsonArray.isEmpty()){
            // 只返回调换位置的两个id  否则不满足排序条件
            if(jsonArray.size() != 2){
                this.writeErrorResponse(response,"排序规则传值有误");
                return;
            }
            List<Map<String, Object>> list = JsonHelper.jsonToMapList(jsonArray);
            int id = Integer.valueOf(list.get(0).get("id").toString());
            int id1 = Integer.valueOf(list.get(1).get("id").toString());
            DeliverPlace deliverPlace = DeliverPlaceManager.getInstance().getEntity(id);
            DeliverPlace deliverPlace1 = DeliverPlaceManager.getInstance().getEntity(id1);
            int displayOrderTemp = deliverPlace.getDisplayOrder();
            deliverPlace.setDisplayOrder(deliverPlace1.getDisplayOrder());
            deliverPlace1.setDisplayOrder(displayOrderTemp);

            DeliverPlaceManager.getInstance().update(deliverPlace);
            DeliverPlaceManager.getInstance().update(deliverPlace1);
        }

        String sql = "select id, name, display_order sort from deliver_place where deliver_line_id in ( select id from deliver_line where deliverer_id = " + delivererId + ") order by display_order";
        ArrayList<LinkedHashMap<String, Object>> list = MyBatisManager.getInstance().executeHashMapList(sql);
        List<Map<String, Object>> deliverPlaceList = new ArrayList<>();
        for(Map map : list){
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", map.get("id").toString());
            dataMap.put("name", map.get("name").toString());
            dataMap.put("sort", map.get("sort").toString());
            deliverPlaceList.add(dataMap);
        }
        this.writeSuccessResponse(response, list);
    }
}
