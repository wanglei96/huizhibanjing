package BRP.service;

import BRP.data.BookOrder;
import BRP.data.ViewWaybill;
import BRP.data.ViewWaybillItem;
import BRP.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.NumberStringHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 配送员小程序-配送单
 */
public class GetDeliveryBookOrderListServiceHandler extends ServiceHandler {

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
            BookOrder bookOrder = BookOrderManager.getInstance().getEntity(id);
            BookOrder bookOrder1 = BookOrderManager.getInstance().getEntity(id1);
            int displayOrderTemp = bookOrder.getDisplayOrder();
            bookOrder.setDisplayOrder(bookOrder1.getDisplayOrder());
            bookOrder1.setDisplayOrder(displayOrderTemp);

            BookOrderManager.getInstance().update(bookOrder);
            BookOrderManager.getInstance().update(bookOrder1);
        }

        // 获取当前配送员的配送订单
        List<ViewWaybill> list= ViewWaybillManager.getInstance().getList("deliverer_id = " + delivererId + " and book_order_borrow_status_code in ('Delivering','Received','ErrorProcessing','ErrorProcessed') and DATE(deliver_time) = CURDATE() order by display_order,display_order_borrow_status_display_order,deliver_place_id");

        Map<String, Integer> deliverLineCountMap = new HashMap<>();
        Map<String, Integer> deliverPlaceCountMap = new HashMap<>();

        List<Map<String, Object>> waybillList = new ArrayList<>();
        for(ViewWaybill viewWaybill : list){
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id",viewWaybill.getId());
            map.put("name",viewWaybill.getName());
            int deliverLineCount = deliverLineCountMap.getOrDefault(viewWaybill.getDeliverLineName(),0) + 1;
            deliverLineCountMap.put(viewWaybill.getDeliverLineName(), deliverLineCount);
            map.put("deliverLineName",viewWaybill.getDeliverLineName() + deliverLineCount + "号");
            int deliverPlaceCount = deliverPlaceCountMap.getOrDefault(viewWaybill.getDeliverPlaceName(),0) + 1;
            deliverPlaceCountMap.put(viewWaybill.getDeliverPlaceName(), deliverPlaceCount);
            map.put("deliverPlaceName",viewWaybill.getDeliverPlaceName() + deliverPlaceCount + "号");
            map.put("address",viewWaybill.getAddress());
            map.put("entranceGuard",viewWaybill.getEntranceGuard());
            map.put("bookboxNo",viewWaybill.getBookboxNo());
            map.put("mobilePhone",viewWaybill.getMobilePhone());
            map.put("errorRemark",viewWaybill.getErrorRemark());
            map.put("bookOrderBorrowStatusCode",viewWaybill.getBookOrderBorrowStatusCode());
            map.put("sendNum",BookOrderItemManager.getInstance().getCount("book_order_id = " + viewWaybill.getId() + " and book_order_type_code = 'Borrow'").toString());
            map.put("takeNum",BookOrderItemManager.getInstance().getCount("book_order_id = " + viewWaybill.getId() + " and book_order_type_code = 'Return'").toString());
            // 获取当前配送单需要配送的书籍
            List<Map<String, String>> viewWaybillItemMapList = new ArrayList<>();
            List<ViewWaybillItem> viewWaybillItemList = ViewWaybillItemManager.getInstance().getList("book_order_id = " + viewWaybill.getId() + " order by book_order_type_code,display_order");
            for(ViewWaybillItem viewWaybillItem : viewWaybillItemList){
                Map<String, String> viewWaybillItemMap = new LinkedHashMap<>();
                viewWaybillItemMap.put("bookName", viewWaybillItem.getBookName());
                viewWaybillItemMap.put("bookNo", viewWaybillItem.getBookNo());
                viewWaybillItemMap.put("deliverStatusName", viewWaybillItem.getDeliverStatusName());
                viewWaybillItemMap.put("bookOrderTypeName", viewWaybillItem.getBookOrderTypeName());
                viewWaybillItemMapList.add(viewWaybillItemMap);
            }
            map.put("bookList", viewWaybillItemMapList);
            waybillList.add(map);
        }
        this.writeSuccessResponse(response, waybillList);
    }
}
