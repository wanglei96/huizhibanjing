package BRP.service;

import BRP.model.BookManager;
import BRP.model.BookSkuManager;
import net.sf.json.JSONObject;
import org.w3c.dom.Node;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.XmlDocumentHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.util.*;

public class GetNewBookInfoListServiceHandler extends GetListServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String sn = JsonHelper.getString(jData, "sn");
        String bookName = JsonHelper.getString(jData, "bookName");
        String bookcaseName = JsonHelper.getString(jData, "bookcaseName");
        String author = JsonHelper.getString(jData, "author");
        Integer bookSeriesId = JsonHelper.getInt(jData, "bookSeriesId");
        Integer bookcaseId = JsonHelper.getInt(jData, "bookcaseId");
        Integer bookSkuId = JsonHelper.getInt(jData, "bookSkuId");
        String condition = JsonHelper.getString(jData, "condition");
        String bookStatusCode = JsonHelper.getString(jData, "bookStatusCode");
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        String orderBy = JsonHelper.getString(jData, "orderBy");

        MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
        conditionBuilder.add(condition);
        conditionBuilder.addEqualCondition("book.company_id", companyId);
        conditionBuilder.addEqualCondition("book.book_sku_id", bookSkuId);
        conditionBuilder.addEqualCondition("book.bookcase_id", bookcaseId);
        conditionBuilder.addEqualCondition("book_sku.book_series_id", bookSeriesId);
        conditionBuilder.addEqualCondition("book.book_status_code", bookStatusCode);
        conditionBuilder.addLikeCondition("book_sku.book_name", bookName);
        conditionBuilder.addLikeCondition("book_sku.author", author);
        conditionBuilder.addLikeCondition("book.sn", sn);
        conditionBuilder.addLikeCondition("bookcase.name", bookcaseName);
        String sqlCondition = conditionBuilder.toString();

        String sqlWhere = "";
        if (sqlCondition != null && !sqlCondition.equals("")) {
            sqlWhere = " where " + sqlCondition;
        }
        ArrayList<LinkedHashMap<String, Object>> alData =
                this.getBookInfoList(pageIndex, pageSize, sqlWhere, orderBy);



        MySqlConditionBuilder countBuilder = new MySqlConditionBuilder();
        countBuilder.add(condition);
        countBuilder.addEqualCondition("company_id", companyId);
        countBuilder.addEqualCondition("book_series_id", bookSeriesId);
        countBuilder.addEqualCondition("book_sku_id", bookSkuId);
        countBuilder.addEqualCondition("bookcase_id", bookcaseId);
        countBuilder.addEqualCondition("book_status_code", bookStatusCode);
        countBuilder.addLikeCondition("book_name", bookName);
        countBuilder.addLikeCondition("author", author);
        countBuilder.addLikeCondition("sn", sn);
        countBuilder.addLikeCondition("bookcase_name", bookcaseName);
        String sqlCountCondition = countBuilder.toString();

        String sqlCountWhere = "";
        if (sqlCountCondition != null && !sqlCountCondition.equals("")) {
            sqlCountWhere = " where " + sqlCountCondition;
        }
        String sqlCount = "SELECT COUNT(*) AS count FROM view_book " + sqlCountWhere;
        Integer recordCount = MyBatisManager.getInstance().executeInt(sqlCount);
        ListInfo listInfo = this.createListInfo();
        listInfo.setDataList(alData);
        // 当前页
        listInfo.setPageIndex(pageIndex);
        // 分页大小
        listInfo.setPageSize(pageSize);
        // 记录数
        listInfo.setRecordCount(recordCount);
        // 页数
        int pageCount = ServiceHandlerHelper.getPageCount(recordCount, pageSize);
        listInfo.setPageCount(pageCount);
        this.newProcessListInfo(listInfo);
        this.writeSuccessResponse(response, listInfo);
    }

    private ArrayList<LinkedHashMap<String, Object>> getBookInfoList(Integer pageIndex, Integer pageSize, String sqlWhere, String orderBy) throws Exception {
        String sqlLimit = "";
        if (pageSize > 0) {
            sqlLimit = " limit " + String.valueOf(pageIndex * pageSize) + "," + pageSize;
        }
        String sqlOrderBy = "";
        if (orderBy != null && !orderBy.equals("")) {
            sqlOrderBy = " order by  " + orderBy;
        }
        String sql = String.format("select T1.*\n" +
                ",\n" +
                "    GROUP_CONCAT(DISTINCT book_tag.name SEPARATOR ',') AS bookTagNames,\n" +
                "    GROUP_CONCAT(DISTINCT age_group.name SEPARATOR ',') AS ageGroupNames\n" +
                "from \n" +
                "(\n" +
                "SELECT\n" +
                "    book.id AS id,\n" +
                "    book.book_sku_id AS book_sku_id,\n" +
                "    book.sn AS sn,\n" +
                "    book.borrow_status_code AS borrow_status_code,\n" +
                "    book.book_status_code AS book_status_code,\n" +
                "    book.is_available AS is_available,\n" +
                "    book.company_id AS company_id,\n" +
                "    book.member_id AS member_id,\n" +
                "    book.bookcase_id AS bookcase_id,\n" +
                "    book.remark AS remark,\n" +
                "    book.offline_time AS offline_time,\n" +
                "    book.offline_reason AS offline_reason,\n" +
                "    book.offline_approver_id AS offline_approver_id,\n" +
                "    book.is_damaged AS is_damaged,\n" +
                "    book.create_time AS create_time,\n" +
                "    book.update_time AS update_time,\n" +
                "    book_sku.book_name AS book_name,\n" +
                "    book_sku.image_file_id AS image_file_id,\n" +
                "    book_sku.author AS author,\n" +
                "    book_sku.book_series_id AS book_series_id,\n" +
                "    book_sku.press AS press,\n" +
                "    book_sku.price AS price,\n" +
                "    book_sku.ISBN AS ISBN,\n" +
                "    book_sku.language_code AS language_code,\n" +
                "    book_sku.binding_type_code AS binding_type_code,\n" +
                "    book_sku.dangdang_link AS dangdang_link,\n" +
                "    book_sku.introduction AS introduction,\n" +
                "    book_sku.is_online AS is_online,\n" +
                "    image_file.path AS image_file_path,\n" +
                "    company.company_name AS company_name,\n" +
                "    book_series.name AS book_series_name,\n" +
                "    language.name AS language_name,\n" +
                "    binding_type.name AS binding_type_name,\n" +
                "    bookcase.name AS bookcase_name,\n" +
                "    book_status.name AS book_status_name,\n" +
                "    borrow_status.name AS borrow_status_name,\n" +
                "    member.name AS member_name\n" +
                "FROM\n" +
                "    book\n" +
                "    LEFT JOIN company ON book.company_id = company.id\n" +
                "    LEFT JOIN bookcase ON book.bookcase_id = bookcase.id\n" +
                "    LEFT JOIN book_sku ON book.book_sku_id = book_sku.id\n" +
                "    LEFT JOIN member ON book.member_id = member.id\n" +
                "    LEFT JOIN sys_upload_file image_file ON book_sku.image_file_id = image_file.id\n" +
                "    LEFT JOIN book_series ON book_sku.book_series_id = book_series.id\n" +
                "    LEFT JOIN language ON book_sku.language_code = language.code\n" +
                "    LEFT JOIN binding_type ON book_sku.binding_type_code = binding_type.code\n" +
                "    LEFT JOIN book_status ON book.book_status_code = book_status.code\n" +
                "    LEFT JOIN borrow_status ON book.borrow_status_code = borrow_status.code\n" +
                "  %s %s %s\n" +
                ") T1\n" +
                "    LEFT JOIN book_sku_book_tag ON book_sku_book_tag.book_sku_id = T1.book_sku_id\n" +
                "    LEFT JOIN book_tag ON book_tag.id = book_sku_book_tag.book_tag_id\n" +
                "    LEFT JOIN book_sku_age_group ON book_sku_age_group.book_sku_id = T1.book_sku_id\n" +
                "    LEFT JOIN age_group ON age_group.id = book_sku_age_group.age_group_id\n" +
                "group by\n" +
                " T1.id,\n" +
                "    T1.book_sku_id,\n" +
                "    T1.sn,\n" +
                "    T1.borrow_status_code,\n" +
                "    T1.book_status_code,\n" +
                "    T1.is_available,\n" +
                "    T1.company_id,\n" +
                "    T1.member_id,\n" +
                "    T1.bookcase_id,\n" +
                "    T1.remark,\n" +
                "    T1.offline_time,\n" +
                "    T1.offline_reason,\n" +
                "    T1.offline_approver_id,\n" +
                "    T1.is_damaged,\n" +
                "    T1.create_time,\n" +
                "    T1.update_time,\n" +
                "    T1.book_name,\n" +
                "    T1.image_file_id,\n" +
                "    T1.author,\n" +
                "    T1.book_series_id,\n" +
                "    T1.press,\n" +
                "    T1.price,\n" +
                "    T1.ISBN,\n" +
                "    T1.language_code,\n" +
                "    T1.binding_type_code,\n" +
                "    T1.dangdang_link,\n" +
                "    T1.introduction,\n" +
                "    T1.is_online,\n" +
                "    T1.image_file_path,\n" +
                "    T1.company_name,\n" +
                "    T1.book_series_name,\n" +
                "    T1.language_name,\n" +
                "    T1.binding_type_name,\n" +
                "    T1.bookcase_name,\n" +
                "    T1.book_status_name,\n" +
                "    T1.borrow_status_name,\n" +
                "   T1. member_name \n"+
                "order by T1.sn desc", sqlWhere, sqlOrderBy, sqlLimit);
        return MyBatisManager.getInstance().executeHashMapList(sql);
    }

    protected void newProcessListInfo(ListInfo listInfo) throws Exception {
        ArrayList<LinkedHashMap<String, Object>> alData = (ArrayList<LinkedHashMap<String, Object>>) listInfo
                .getDataList();
        Node configNode = this.getConfigNode();
        String encryptFields = XmlDocumentHelper.getAttribute(configNode, "EncryptFields");
        for (LinkedHashMap<String, Object> currentDataItem : alData) {
            LinkedHashMap<String, Object> lhmTemp = new LinkedHashMap<String, Object>();
            Iterator<Map.Entry<String, Object>> iterator = currentDataItem.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> currentEntry = iterator.next();
                String key = currentEntry.getKey();
                Object objValue = currentEntry.getValue();
                if (objValue == null) {
                    continue;
                }
                if (objValue instanceof java.sql.Timestamp) {
                    java.sql.Timestamp tsValue = (java.sql.Timestamp) objValue;
                    String strValue = DateHelper.format(tsValue, "yyyy-MM-dd HH:mm:ss");
                    currentDataItem.put(key, tsValue);
                    // 保存要添加的建值对到临时Map中
                    lhmTemp.put(key + "_text", strValue);
                } else if (objValue instanceof java.time.LocalDateTime) {
                    java.time.LocalDateTime ldtValue = (java.time.LocalDateTime) objValue;
                    ZoneId zoneId = ZoneId.systemDefault();
                    Date date = Date.from(ldtValue.atZone(zoneId).toInstant());
                    String strValue = DateHelper.format(date, "yyyy-MM-dd HH:mm:ss");
                    currentDataItem.put(key, ldtValue);
                    // 保存要添加的建值对到临时Map中
                    lhmTemp.put(key + "_text", strValue);
                } else if (objValue instanceof java.sql.Time) {
                    java.sql.Time timeValue = (java.sql.Time) objValue;
                    String strValue = timeValue.toString();
                    currentDataItem.put(key, strValue);
                }
                if (("," + encryptFields + ",").contains("," + key + ",")) {
                    currentDataItem.put(key, "");
                }
            }
            // 添加新键值对
            for (String key : lhmTemp.keySet()) {
                Object objValue = lhmTemp.get(key);
                currentDataItem.put(key, objValue);
            }
        }
        com.alibaba.fastjson2.JSONArray jarrData = JsonHelper.toFastJSONArray(alData,
                "All");
        listInfo.setDataList(jarrData);
    }

}
