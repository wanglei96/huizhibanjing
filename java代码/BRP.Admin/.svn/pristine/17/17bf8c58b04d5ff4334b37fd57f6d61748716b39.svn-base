package BRP.model;

import BRP.data.BookSkuContext;
import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.model.ExportContext;
import strosoft.sys.model.ExportHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ExportBookSkuHandler extends ExportHandler {
    /**
     * 取得数据
     *
     * @param exportContext
     * @return
     * @throws Exception
     */
    protected Object getData(ExportContext exportContext) throws Exception {
        String data = exportContext.getRequest().getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
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
        return alData;
    }
}
