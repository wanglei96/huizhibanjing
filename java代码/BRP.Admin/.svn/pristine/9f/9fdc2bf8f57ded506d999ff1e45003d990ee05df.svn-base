package BRP.service;

import strosoft.app.common.MyBatisManager;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.service.ServiceHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * 查询借阅记录，按照日分组
 */
public class GetHomeNewBookInfoListServiceHandler extends GetListServiceHandler {
    /*
     * 处理数据
     */
    protected void processListInfo(ListInfo listInfo) throws Exception {
        super.processListInfo(listInfo);

        ArrayList<LinkedHashMap<String, Object>> dataList = (ArrayList<LinkedHashMap<String, Object>>) listInfo.getDataList();

        Map<String, List<LinkedHashMap<String, Object>>> homeNewBookInfoMap = new HashMap<String, List<LinkedHashMap<String, Object>>>();

        for (LinkedHashMap<String, Object> item : dataList) {
            if (item.get("book_tag_name") != null && !homeNewBookInfoMap.containsKey(item.get("book_tag_name"))) {
                homeNewBookInfoMap.put(item.get("book_tag_name").toString(), new ArrayList<LinkedHashMap<String, Object>>());
            }
            if (item.get("book_tag_name") != null) {
                homeNewBookInfoMap.get(item.get("book_tag_name").toString()).add(item);
            }
        }

        List<LinkedHashMap<String, Object>> homeNewBookInfoList = new ArrayList<>();
        for (String key : homeNewBookInfoMap.keySet()) {
            LinkedHashMap<String, Object> homeNewBookInfo = new LinkedHashMap<>();
            homeNewBookInfo.put("bookTagName", key);
            homeNewBookInfo.put("booksList", homeNewBookInfoMap.get(key));
            homeNewBookInfoList.add(homeNewBookInfo);
        }
        listInfo.setDataList(homeNewBookInfoList);
    }
}
