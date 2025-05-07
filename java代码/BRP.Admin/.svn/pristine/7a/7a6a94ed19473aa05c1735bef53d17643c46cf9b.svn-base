package BRP.service;

import BRP.data.*;
import BRP.model.ViewBookOrderItemManager;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.service.GetListServiceHandlerContext;
import strosoft.app.util.DateHelper;
import strosoft.app.util.StringHelper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取配送单列表
 */
public class GetOldDeliveryOrderInfoListServiceHandler extends GetListServiceHandler {
    @Override
    protected void processListInfo(ListInfo listInfo) throws Exception {
        super.processListInfo(listInfo);
        ArrayList<LinkedHashMap<String, Object>> dataList = (ArrayList<LinkedHashMap<String, Object>>) listInfo.getDataList();

        GetListServiceHandlerContext context = this.getContext();

        StringBuilder allIds = new StringBuilder();
        boolean isFirst = true;

        for (LinkedHashMap<String, Object> item : dataList) {
            String ids = (String) item.get("ids");
            if (ids != null && !ids.isEmpty()) {
                for (String id : ids.split(",")) {
                    String trimmedId = id.trim();
                    if (isFirst) {
                        allIds.append(trimmedId);
                        isFirst = false;
                    } else {
                        allIds.append(",").append(trimmedId);
                    }
                }
            }
        }

        String idsAsString = allIds.toString();
        List<ViewBookOrderItem> lstViewBookOrderItem = new ArrayList<>();
        if (!StringHelper.isNullOrEmpty(idsAsString)) {
            String condition = "id in (" + idsAsString + ")";
            lstViewBookOrderItem = ViewBookOrderItemManager.getInstance().getList(condition, "create_time desc");
        }


        //List<ViewBookOrderItem> lstViewBookOrderItem = JsonHelper.toJavaList(ViewBookOrderItem.class, dataList);

        Set<Integer> setBookOrderId = new LinkedHashSet<>(lstViewBookOrderItem.stream()
                .map(ViewBookOrderItem::getBookOrderId)
                .collect(Collectors.toList()));

        List<DeliveryOrderInfo> lstDeliveryOrderInfo = new ArrayList<DeliveryOrderInfo>();

        /*Map<String, Integer> areaNameCount = new HashMap<>();
        Map<String, Integer> lineNameCount = new HashMap<>();
        Map<String, Integer> placeNameCount = new HashMap<>();*/

        for (Integer bookOrderId : setBookOrderId) {
            List<ViewBookOrderItem> children =
                    lstViewBookOrderItem.stream().filter(viewBookOrderItem -> viewBookOrderItem.getBookOrderId().equals(bookOrderId)).collect(Collectors.toList());

            DeliveryOrderInfo deliveryOrderInfo = new DeliveryOrderInfo();

            String deliverAreaName = children.get(0).getDeliverAreaName();
            Integer deliverAreaNumber = children.get(0).getDeliverAreaNumber();
            /*int currentCount = areaNameCount.getOrDefault(deliverAreaName, 0) + 1;
            areaNameCount.put(deliverAreaName, currentCount);*/
            if (deliverAreaNumber != null) {
                deliveryOrderInfo.setDeliverAreaName(deliverAreaName + deliverAreaNumber + "号");
            } else {
                deliveryOrderInfo.setDeliverAreaName(deliverAreaName);
            }


            String deliverLineName = children.get(0).getDeliverLineName();
            /*int lineCount = lineNameCount.getOrDefault(deliverLineName, 0) + 1;
            lineNameCount.put(deliverLineName, lineCount);*/
            deliveryOrderInfo.setDeliverLineName(deliverLineName);

            String deliverPlaceName = children.get(0).getDeliverPlaceName();
            /*int placeCount = placeNameCount.getOrDefault(deliverPlaceName, 0) + 1;
            placeNameCount.put(deliverPlaceName, placeCount);*/
            deliveryOrderInfo.setDeliverPlaceName(deliverPlaceName);

            deliveryOrderInfo.setMemberEntranceGuard(children.get(0).getMemberEntranceGuard());
            deliveryOrderInfo.setBookOrderId(children.get(0).getBookOrderId());
            deliveryOrderInfo.setDeliverTime(children.get(0).getDeliverTime());
            deliveryOrderInfo.setMemberId(children.get(0).getMemberId());
            deliveryOrderInfo.setMemberCardId(children.get(0).getMemberCardId());
            deliveryOrderInfo.setMemberCardStatusCode(children.get(0).getMemberCardStatusCode());
            deliveryOrderInfo.setMemberNo(children.get(0).getMemberNo());
            deliveryOrderInfo.setMemberName(children.get(0).getMemberName());
            deliveryOrderInfo.setMemberAddress(children.get(0).getAddress());
            deliveryOrderInfo.setMemberMobilePhone(children.get(0).getMemberMobilePhone());
            deliveryOrderInfo.setDeliverAreaId(children.get(0).getDeliverAreaId());
            if (children.get(0).getDeliverTime() != null) {
                String deliverTimeText = DateHelper.timestampToString(children.get(0).getDeliverTime(), "yyyy-MM-dd");
                deliveryOrderInfo.setDeliverTimeText(deliverTimeText);
            } else {
                deliveryOrderInfo.setDeliverTimeText("");
            }
            deliveryOrderInfo.setErrorRemark(children.get(0).getErrorRemark());
            deliveryOrderInfo.setDeliverLineId(children.get(0).getDeliverLineId());
            deliveryOrderInfo.setDelivererId(children.get(0).getDelivererId());
            deliveryOrderInfo.setDelivererName(children.get(0).getDelivererName());
            deliveryOrderInfo.setBookOrderBorrowStatusCode(children.get(0).getBookOrderBorrowStatusCode());
            deliveryOrderInfo.setBookOrderReturnStatusCode(children.get(0).getBookOrderReturnStatusCode());
            deliveryOrderInfo.setMemberCardNo(children.get(0).getMemberCardNo());
            deliveryOrderInfo.setBookOrderBorrowStatusName(children.get(0).getBookOrderBorrowStatusName());
            deliveryOrderInfo.setBookOrderReturnStatusName(children.get(0).getBookOrderReturnStatusName());
            //获取送书数量
            long sendCount = children.stream()
                    .filter(item -> BookOrderTypeCodes.Borrow.equals(item.getBookOrderTypeCode())).count();
            deliveryOrderInfo.setSendCount(sendCount);
            //获取送书数量
            long fetchCount = children.stream()
                    .filter(item -> BookOrderTypeCodes.Return.equals(item.getBookOrderTypeCode())).count();
            deliveryOrderInfo.setFetchCount(fetchCount);
            deliveryOrderInfo.setChildren(children);
            lstDeliveryOrderInfo.add(deliveryOrderInfo);
        }
        listInfo.setDataList(lstDeliveryOrderInfo);
    }
}
