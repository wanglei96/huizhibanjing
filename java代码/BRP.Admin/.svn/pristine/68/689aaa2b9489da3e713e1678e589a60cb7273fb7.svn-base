package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 书架汇总明细 根据书柜分组
 */
public class GetBookCaseDetailListServiceHandler  extends GetListServiceHandler {
    @Override
    protected void processListInfo(ListInfo listInfo) throws Exception {
        super.processListInfo(listInfo);
        ArrayList<LinkedHashMap<String, Object>> dataList = (ArrayList<LinkedHashMap<String, Object>>) listInfo.getDataList();
        List<ViewBookOutOrder> lstViewBookOutOrder = JsonHelper.toJavaList(ViewBookOutOrder.class, dataList);
        Set<Integer> bookBookcaseIdList = lstViewBookOutOrder.stream().map(ViewBookOutOrder::getBookBookcaseId).collect(Collectors.toSet());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Integer bookcaseIdItem : bookBookcaseIdList) {
            if(bookcaseIdItem == null){
                continue;
            }
            int i = 1;
            List<Map<String, Object>> list1 = new ArrayList<>();
            for(ViewBookOutOrder viewBookOutOrder : lstViewBookOutOrder){
                if( viewBookOutOrder.getBookBookcaseId() == null){
                    continue;
                }
                if(viewBookOutOrder.getBookBookcaseId().equals(bookcaseIdItem)){
                    Map<String, Object> map = new HashMap<>();
                    map.put("no", i++);
                    map.put("bookName", viewBookOutOrder.getBookName());
                    map.put("bookSn",viewBookOutOrder.getBookSn());
                    list1.add(map);
                }
            }
            Bookcase bookcase = BookcaseManager.getInstance().getEntity(bookcaseIdItem);
            Map<String, Object> map = new HashMap<>();
            map.put("bookcaseName", bookcase.getName());
            map.put("children", list1);
            list.add(map);
        }
        listInfo.setDataList(list);
    }
}
