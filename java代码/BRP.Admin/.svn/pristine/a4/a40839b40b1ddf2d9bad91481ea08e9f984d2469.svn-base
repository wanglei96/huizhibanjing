package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.model.ListInfo;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.PagerHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.StringJoiner;

public class GetBookInfoListServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //SELECT * from view_book where book_sku_id in
        //	(SELECT id from book_sku WHERE id in (SELECT book_sku_id from book_sku_book_tag where book_tag_id=1) and
        //	(book_name like'%朝花夕拾%' or book_series_name like '%我爱宝贝%' or ISBN like '%1243298784%')) and company_id=29 ORDER BY create_time DESC;
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        Integer bookTagId = JsonHelper.getInt(jData, "bookTagId");
        String searchContent = JsonHelper.getString(jData, "searchContent");
        String condition = getSqlBuilder(bookTagId, searchContent);
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        if (pageIndex == null) {
            pageIndex = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        String sqlLimit = "";
        if (pageSize > 0) {
            sqlLimit = " limit " + String.valueOf(pageIndex * pageSize) + "," + pageSize;
        }

        String sql =
                String.format("SELECT * from view_book where book_sku_id in \n" +
                        "\t (SELECT id from book_sku %s) and company_id=" + companyId + " ORDER BY create_time DESC %s", condition,sqlLimit);

        ArrayList<LinkedHashMap<String, Object>> linkedHashMaps = MyBatisManager.getInstance().executeHashMapList(sql);
        ListInfo listInfo = new ListInfo();
        listInfo.setPageSize(pageSize);
        listInfo.setPageIndex(pageIndex);
        listInfo.setRecordCount(linkedHashMaps.size());
        listInfo.setPageCount(PagerHelper.getPageCount(listInfo.getRecordCount(), listInfo.getPageSize()));
        listInfo.setDataList(linkedHashMaps);
        writeSuccessResponse(response, listInfo);
    }

    private String getSqlBuilder(Integer bookTagId, String searchContent) {
        StringBuilder condition = new StringBuilder();

        if (bookTagId != null) {
            condition.append("where id in (SELECT book_sku_id from book_sku_book_tag where book_tag_id=" + bookTagId + ")");
        }

        StringBuilder orCondition = new StringBuilder();
        boolean needOr = false;
        if (searchContent != null && !searchContent.trim().isEmpty()) {
            orCondition.append("(book_name like'%" + searchContent + "%' or book_series_name like '%" + searchContent + "%' or ISBN like '%" + searchContent + "%')");
            needOr = true;
        }
        if (needOr) {
            if (bookTagId == null) {
                orCondition.insert(0, " where (");
            } else {
                orCondition.insert(0, "and (");
            }
            orCondition.append(")");
            condition.append(orCondition.toString());
        }
        return condition.toString();
    }
}