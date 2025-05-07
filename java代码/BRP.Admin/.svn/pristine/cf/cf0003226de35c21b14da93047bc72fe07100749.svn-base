package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * 取得用户菜单列表
 */
public class GetBookSkuListByBookTagIdServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Integer isHasBookSku = JsonHelper.getInt(jData, "isHasBookSku");
        String bookInfoQuery = JsonHelper.getString(jData, "bookInfoQuery");
        Integer bookTagId = JsonHelper.getInt(jData, "bookTagId");
        ArrayList<LinkedHashMap<String, Object>> lhmData = this.getAllBookSkuByBookTagId(
                companyId, bookInfoQuery, bookTagId, isHasBookSku);
        JSONArray result = JsonHelper.toJSONArray(lhmData);
        this.writeSuccessResponse(response, result);
    }

    private ArrayList<LinkedHashMap<String, Object>> getAllBookSkuByBookTagId(
            Integer companyId,
            String bookInfoQuery, Integer bookTagId, Integer isHasBookSku) throws Exception {
        String sql = String.format("select bs.*,\n" +
                        "                        CASE WHEN bt.book_sku_id IS NOT NULL THEN true ELSE false END AS has_book_sku\n" +
                        "                        FROM view_book_sku bs\n" +
                        "                        LEFT JOIN book_sku_book_tag bt ON bt.book_sku_id = bs.id and bt.book_tag_id = %d\n" +
                        "                        where bs.company_id = %d %s\n" +
                        "                        HAVING has_book_sku = %d;",
                bookTagId, companyId,
                StringHelper.isNullOrEmpty(bookInfoQuery) ? "" : "and (bs.book_name like '%" + bookInfoQuery + "%' or bs.book_series_name like '%" + bookInfoQuery + "%')",
                isHasBookSku);
        ArrayList<LinkedHashMap<String, Object>> lhmData = MyBatisManager.getInstance().executeHashMapList(sql);
        return lhmData;
    }
}
