package BRP.service;

import BRP.data.BookOrderTypeCodes;
import BRP.data.DeliveryOrderInfo;
import BRP.data.ViewBookOrder;
import BRP.data.ViewBookOrderItem;
import BRP.model.CompanySettingManager;
import BRP.model.ViewBookOrderItemManager;
import net.sf.json.JSONArray;
import strosoft.app.common.MyBatisManager;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.service.GetListServiceHandlerContext;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取配送单列表
 */
public class GetNewDeliveryOrderInfoListServiceHandler extends GetListServiceHandler {
    @Override
    protected void processListInfo(ListInfo listInfo) throws Exception {
        super.processListInfo(listInfo);
        ArrayList<LinkedHashMap<String, Object>> dataList = (ArrayList<LinkedHashMap<String, Object>>) listInfo.getDataList();

        List<DeliveryOrderInfo> lstDeliveryOrderInfo = JsonHelper.toJavaList(DeliveryOrderInfo.class, dataList);
        Integer companyId = null;
        String returnButNotBorrowed = "false";
        if(lstDeliveryOrderInfo.size() > 0){
            companyId = lstDeliveryOrderInfo.get(0).getCompanyId();
            //判断门店是否开启直接未还
            returnButNotBorrowed = CompanySettingManager.getInstance().getCompanySettingValueByCode(companyId, "ReturnButNotBorrowed");
        }
        String idString = lstDeliveryOrderInfo.stream()
                .map(DeliveryOrderInfo::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        List<ViewBookOrderItem> lstViewBookOrderItem = this.getChildrenList(idString);

        for (int i = 0; i < lstDeliveryOrderInfo.size(); i++) {
            DeliveryOrderInfo deliveryOrderInfo = lstDeliveryOrderInfo.get(i);
            Integer bookOrderId = deliveryOrderInfo.getId();
            deliveryOrderInfo.setBookOrderId(bookOrderId);
            List<ViewBookOrderItem> children =
                    lstViewBookOrderItem.stream().filter
                            (viewBookOrderItem -> viewBookOrderItem.getBookOrderId()
                                    .equals(bookOrderId)).collect(Collectors.toList());
            String deliverAreaName = deliveryOrderInfo.getDeliverAreaName();
            Integer deliverAreaNumber = deliveryOrderInfo.getDeliverAreaNumber();
            if (deliverAreaNumber != null) {
                deliveryOrderInfo.setDeliverAreaName(deliverAreaName + deliverAreaNumber + "号");
            } else {
                deliveryOrderInfo.setDeliverAreaName(deliverAreaName);
            }
            if (deliveryOrderInfo.getDeliverTime() != null) {
                String deliverTimeText = DateHelper.timestampToString(deliveryOrderInfo.getDeliverTime(), "yyyy-MM-dd");
                deliveryOrderInfo.setDeliverTimeText(deliverTimeText);
            } else {
                deliveryOrderInfo.setDeliverTimeText("");
            }
            //获取送书数量
            long sendCount = children.stream()
                    .filter(item -> BookOrderTypeCodes.Borrow.equals(item.getBookOrderTypeCode())).count();
            deliveryOrderInfo.setSendCount(sendCount);
            //获取送书数量
            long fetchCount = children.stream()
                    .filter(item -> BookOrderTypeCodes.Return.equals(item.getBookOrderTypeCode())).count();
            deliveryOrderInfo.setFetchCount(fetchCount);
            deliveryOrderInfo.setChildren(children);
        }
        listInfo.setDataList(lstDeliveryOrderInfo);
    }

    /**
     * 获取订单下所有子表
     *
     * @param idString
     */
    private List<ViewBookOrderItem> getChildrenList(String idString) throws Exception {
        if (StringHelper.isNullOrEmpty(idString)) {
            return null;
        }
        String sql = String.format("select book_order_item.id AS id,\n" +
                "\t\t\t book_order_item.book_order_id AS bookOrderId,\n" +
                "\t     book_order_item.book_id AS bookId,\n" +
                "\t\t\t\tbook_order_item.is_damage AS isDamage,\n" +
                "\t\t\t\tbook_order_item.book_order_type_code AS bookOrderTypeCode,\n" +
                "\t\t\t\tbook_order_item.deliver_status_code AS deliverStatusCode,\n" +
                "\t\t\t\tbook_order_item.is_returned AS isReturned,\n" +
                "\t\t\t\tbook_order_item.book_return_time AS bookReturnTime,\n" +
                "\t\t\t\tbook_order_item.book_in_stocks_time AS bookInStocksTime,\n" +
                "\t\t\t\tbook_order_item.create_time AS createTime,\n" +
                "\t\t\t\tbook_order_item.update_time AS updateTime,\n" +
                "\t     book.sn AS bookSn,\n" +
                "\t     book.book_sku_id AS bookSkuId,\n" +
                "\t\t\t book.borrow_status_code AS borrowStatusCode,\n" +
                "\t\t\t book.bookcase_id AS bookcaseId,\n" +
                "\t\t\t book_sku.book_name AS bookName,\n" +
                "\t\t\t book_sku.book_series_id AS bookSeriesId,\n" +
                "\t\t\t book_sku.binding_type_code AS bindingTypeCode,\n" +
                "\t\t\t book_sku.price AS bookSkuPrice,\n" +
                "\t\t\t bookcase.name AS bookcaseName,\n" +
                "\t\t\t book_series.name AS bookSeriesName,\n" +
                "\t\t\t binding_type.name AS bindingTypeName,\n" +
                "\t\t\t book_order_type.name AS bookOrderTypeName\n" +
                "FROM\n" +
                "(select * from book_order_item where book_order_id in (%s)) as book_order_item\n" +
                "LEFT JOIN book ON book.id = book_order_item.book_id\n" +
                "LEFT JOIN book_order_type ON book_order_item.book_order_type_code = book_order_type.code \n" +
                "LEFT JOIN deliver_status ON book_order_item.deliver_status_code = deliver_status.code \n" +
                "LEFT JOIN book_sku ON book.book_sku_id = book_sku.id \n" +
                "LEFT JOIN sys_upload_file image_file ON book_sku.image_file_id = image_file.id \n" +
                "LEFT JOIN bookcase ON book.bookcase_id = bookcase.id\n" +
                "LEFT JOIN book_series ON book_sku.book_series_id = book_series.id \n" +
                "LEFT JOIN binding_type ON book_sku.binding_type_code = binding_type.code\n" +
                "ORDER BY book_order_type_code\n", idString);
        ArrayList<LinkedHashMap<String, Object>> lData = MyBatisManager.getInstance().executeHashMapList(sql);
        return JsonHelper.toJavaList(ViewBookOrderItem.class, lData);
    }
}
