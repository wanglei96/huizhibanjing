package BRP.service;

import strosoft.app.common.MyBatisManager;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.util.StringHelper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 查询借阅记录，按照日分组
 */
public class GetRequiredBookInfoListServiceHandler extends GetListServiceHandler {
    /*
     * 处理数据
     */
    protected void processListInfo(ListInfo listInfo) throws Exception {
        super.processListInfo(listInfo);

        ArrayList<LinkedHashMap<String, Object>> dataList = (ArrayList<LinkedHashMap<String, Object>>) listInfo.getDataList();

        // 从dataList提取所有唯一的ids
        List<String> allIds = new ArrayList<>();
        for (LinkedHashMap<String, Object> item : dataList) {
            String ids = (String) item.get("ids");
            if (ids != null && !ids.isEmpty()) {
                for (String id : ids.split(",")) {
                    if (!allIds.contains(id.trim())) {
                        allIds.add(id.trim());
                    }
                }
            }
        }

        // 构建SQL查询语句一次性获取所有ids对应的书籍信息
        StringBuilder sql = new StringBuilder(
                "SELECT id,book_sku_id AS bookSkuId, book_name AS bookName, book_sku_image_file_path AS bookSkuImageFilePath" +
                        ",book_sku_small_image_file_path AS bookSkuSmallImageFilePath FROM view_required_book WHERE 1=1");

        // 添加 WHERE 条件
        if (!allIds.isEmpty()) {
            // 使用逗号分隔的 id 列表
            String ids = allIds.stream()
                    .map(String::valueOf)  // 将每个 id 转换为字符串
                    .collect(Collectors.joining(","));

            // 在 SQL 中使用 IN 关键字
            sql.append(" AND id IN (").append(ids).append(")");

            // 添加排序
            sql.append(" ORDER BY create_time DESC");
        }

        String finalSql = sql.toString();

        List<LinkedHashMap<String, Object>> booksList = MyBatisManager.getInstance().executeHashMapList(finalSql);

        // 根据每个项目中的ids映射检索到的数据回dataList
        List<LinkedHashMap<String, Object>> bookInfoList = new ArrayList<>();
        for (LinkedHashMap<String, Object> item : dataList) {
            String ids = (String) item.get("ids");
            String bookSeriesName = (String) item.get("book_series_name");

            LinkedHashMap<String, Object> bookInfo = new LinkedHashMap<>();
            if (StringHelper.isNullOrEmpty(bookSeriesName)) {
                bookInfo.put("bookSeriesName", "无");
            } else {
                bookInfo.put("bookSeriesName", bookSeriesName);
            }


            // 为当前项目中的特定ids过滤booksList
            List<LinkedHashMap<String, Object>> filteredBooksList = new ArrayList<>();
            for (LinkedHashMap<String, Object> book : booksList) {
                String itemId = String.valueOf(book.get("id"));
                if (ids.contains(itemId)) {
                    filteredBooksList.add(book);
                }
            }

            bookInfo.put("booksList", filteredBooksList);
            bookInfoList.add(bookInfo);
        }


        listInfo.setDataList(bookInfoList);
    }
}
