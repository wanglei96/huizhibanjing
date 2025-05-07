package BRP.model;

import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.model.ExportContext;
import strosoft.sys.model.ExportHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ExportBookBorrowTotalHandler extends ExportHandler {
    /**
     * 取得数据
     *
     * @param context
     * @return
     * @throws Exception
     */
    protected Object getData(ExportContext context) throws Exception {
        String data = context.getRequest().getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String bookName = JsonHelper.getString(jData, "bookName");
        String createTimeBegin = JsonHelper.getString(jData, "createTimeBegin");
        String createTimeEnd = JsonHelper.getString(jData, "createTimeEnd");
        if(!StringHelper.isNullOrEmpty(createTimeEnd)){
            createTimeEnd+= " 23:59:59";
        }
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        String orderBy = JsonHelper.getString(jData, "orderBy");
        //获得数据
        ArrayList<LinkedHashMap<String, Object>> alData = BookSkuManager.getInstance().getBookBorrowTotal(
                companyId, bookName, createTimeBegin, createTimeEnd, orderBy, pageIndex, pageSize
        );
        return alData;
    }
}
