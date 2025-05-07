package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GetBookSkuInfoServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer skuId = JsonHelper.getInt(jData, "id");
        String sql = String.format("SELECT \n" +
                "    view_book_sku.id,\n" +
                "    view_book_sku.book_name as bookName, \n" +
                "    view_book_sku.author,\n" +
                "    view_book_sku.company_id as companyId,\n" +
                "    view_book_sku.image_file_path as imageFilePath,\n" +
                "    view_book_sku.small_image_file_path as smallImageFilePath,\n" +
                "    view_book_sku.book_series_id as bookSeriesId,\n" +
                "    view_book_sku.book_series_name as bookSeriesName,\n" +
                "    view_book_sku.introduction,\n" +
                "    GROUP_CONCAT(DISTINCT view_book_sku_book_tag.book_tag_name SEPARATOR ',') AS bookTagNames,\n" +
                "    GROUP_CONCAT(DISTINCT view_book_sku_age_group.age_group_name SEPARATOR ',') AS ageGroupNames,\n" +
                "    MAX(bookcase.name) AS bookcaseName,\n" +
                "    COUNT(book.id) > 0 AS isBookAvailable\n" +
                "FROM \n" +
                "    view_book_sku\n" +
                "LEFT JOIN \n" +
                "    view_book_sku_book_tag ON view_book_sku_book_tag.book_sku_id = view_book_sku.id\n" +
                "LEFT JOIN \n" +
                "    view_book_sku_age_group ON view_book_sku_age_group.book_sku_id = view_book_sku.id\n" +
                "LEFT JOIN \n" +
                "    book ON book.book_sku_id = view_book_sku.id AND book.is_available = 1 AND book.book_status_code = 'On'\n" +
                "LEFT JOIN\n" +
                "    bookcase ON bookcase.id = book.bookcase_id\n" +
                "WHERE \n" +
                "    view_book_sku.id = %d", skuId);
        List<LinkedHashMap<String, Object>> jResult = MyBatisManager.getInstance().executeHashMapList(sql);
        this.writeSuccessResponse(response, jResult);
    }
}
