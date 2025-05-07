package BRP.service;

import BRP.data.*;
import BRP.model.*;
import BRP.vo.PickBookOrderVo;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.NumberStringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 配送小程序-拣书单
 */
public class GetPickBookOrderListServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer delivererId = JsonHelper.getInt(jData, "delivererId");
        String type = JsonHelper.getString(jData, "type");
        // 默认查询所有的拣书单
        String condition = "";
        if(type.equals("today")){
            condition = "and deliver_time = " + DateHelper.getDateString();
        }else if(type.equals("tomorrow")){
            condition = "and deliver_time = " + DateHelper.toDateString(DateHelper.addDays(new Date(), 1));
        }

        List<ViewBookOrder> bookOrderList = ViewBookOrderManager.getInstance().getList("deliverer_id = " + delivererId + " and book_order_return_status_code = 'Retrieving' " + condition);
        List<PickBookOrderVo> pickBookOrderVoList = new ArrayList<>();
        for(ViewBookOrder bookOrder : bookOrderList){
            try {
                Member member = MemberManager.getInstance().getEntity(bookOrder.getMemberId());
                PickBookOrderVo pickBookOrderVo = new PickBookOrderVo();
                pickBookOrderVo.setId(bookOrder.getId());
                pickBookOrderVo.setMemberNo(member.getMemberNo());
                pickBookOrderVo.setName(member.getName());
                pickBookOrderVo.setDeliverLineName(bookOrder.getDeliverLineName());
                pickBookOrderVo.setMobilePhone(member.getMobilePhone());
                pickBookOrderVo.setAddress(member.getAddress());
                List<Map<String, String>> mapList = new ArrayList<>();
                List<BookOrderItem> list = BookOrderItemManager.getInstance().getList("book_order_id = " + bookOrder.getId() + " and book_order_type_code = 'Return'");
                pickBookOrderVo.setPickTotal(list.size());
                pickBookOrderVo.setyPicKNum(BookOrderItemManager.getInstance().getCount("book_order_id = " + bookOrder.getId() + " and book_order_type_code = 'Return' and deliver_status_code = 'Storageed'"));
                pickBookOrderVo.setnPicKNum(BookOrderItemManager.getInstance().getCount("book_order_id = " + bookOrder.getId() + " and book_order_type_code = 'Return' and deliver_status_code = 'Storageing'"));
                for(BookOrderItem bookOrderItem : list){
                    ViewBook viewBook = ViewBookManager.getInstance().getEntity(bookOrderItem.getBookId());
                    Map<String, String> map = new HashMap<>();
                    map.put("id", bookOrderItem.getId().toString());
                    map.put("bookName", viewBook.getBookName());
                    map.put("sn", viewBook.getSn());
                    map.put("imageFilePath", viewBook.getImageFilePath());
                    map.put("bookcaseName", viewBook.getBookcaseName());
                    map.put("deliverStatusCode", bookOrderItem.getDeliverStatusCode());
                    mapList.add(map);
                }
                pickBookOrderVo.setChildBookOrder(mapList);
                pickBookOrderVoList.add(pickBookOrderVo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        this.writeSuccessResponse(response, pickBookOrderVoList);
    }
}
