package BRP.service;

import BRP.data.BookSku;
import BRP.data.BookSkuContext;
import BRP.data.BookSkuInfo;
import BRP.data.MemberCardInfo;
import BRP.model.BookSkuManager;
import BRP.model.MemberManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GetBookSkuInfoListServiceHandler extends GetListServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String ISBN = JsonHelper.getString(jData, "ISBN");
        String bookName = JsonHelper.getString(jData, "bookName");
        String author = JsonHelper.getString(jData, "author");
        Integer bookSeriesId = JsonHelper.getInt(jData, "bookSeriesId");
        Integer bookTagSelectValue = JsonHelper.getInt(jData, "bookTagSelectValue");
        Integer ageGroupSelectValue = JsonHelper.getInt(jData, "ageGroupSelectValue");
        String condition = JsonHelper.getString(jData, "condition");
        String isOnlineQuery = JsonHelper.getString(jData, "isOnlineQuery");
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        String orderBy = JsonHelper.getString(jData, "orderBy");
        BookSkuContext context = new BookSkuContext();
        context.setCompanyId(companyId);
        context.setISBN(ISBN);
        context.setBookName(bookName);
        context.setAuthor(author);
        context.setBookSeriesId(bookSeriesId);
        context.setCondition(condition);
        context.setIsOnlineQuery(isOnlineQuery);
        context.setPageIndex(pageIndex);
        context.setPageSize(pageSize);
        context.setOrderBy(orderBy);
        context.setAgeGroupSelectValue(ageGroupSelectValue);
        context.setBookTagSelectValue(bookTagSelectValue);
        String sqlCondition =BookSkuManager.getInstance().getBookSkuCondition(context);
        String sqlWhere = "";
        if (sqlCondition != null && !sqlCondition.equals("")) {
            sqlWhere = " where " + sqlCondition;
        }
        ArrayList<LinkedHashMap<String, Object>> alData  =
                BookSkuManager.getInstance().getBookSkuInfoList(context,sqlWhere);

        //List<BookSkuInfo> lstBookSkuInfo =  JsonHelper.toJavaList(BookSkuInfo.class, alData);
        String sqlCount = "SELECT COUNT(*) AS count FROM book_sku " + sqlWhere;
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
        this.writeSuccessResponse(response, listInfo);
    }
}
