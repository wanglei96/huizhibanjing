package BRP.service;

import BRP.data.CompanyUser;
import BRP.model.CompanyUserManager;
import net.sf.json.JSONArray;
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

public class GetDelivererTotalServiceHandler extends GetListServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String deliverLineName = JsonHelper.getString(jData, "deliverLineName");
        String name = JsonHelper.getString(jData, "delivererName");
        String deliverTimeBegin = JsonHelper.getString(jData, "deliverTimeBegin");
        String deliverTimeEnd = JsonHelper.getString(jData, "deliverTimeEnd");
        if (!StringHelper.isNullOrEmpty(deliverTimeEnd)) {
            deliverTimeEnd += " 23:59:59";
        }
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        String orderBy = JsonHelper.getString(jData, "orderBy");
        ArrayList<LinkedHashMap<String, Object>> alData =CompanyUserManager.getInstance().getDelivererTotalData(companyId, deliverLineName, name, deliverTimeBegin, deliverTimeEnd, pageIndex, pageSize, orderBy);

        JSONArray jResult = JsonHelper.toJSONArray(alData);
        String sqlCount = String.format("SELECT COUNT(*) AS total_records\n" +
                "FROM (\n" +
                "SELECT cu.id,cu.name,cu.company_id,dl.name AS deliver_line_name\n" +
                "FROM view_company_user cu\n" +
                "LEFT JOIN\n" +
                "    deliver_line dl ON cu.id = dl.deliverer_id\n" +
                "WHERE  cu.is_deliverer = 1 AND cu.company_id= %d\n" +
                "GROUP BY\n" +
                "     cu.id,cu.name,cu.company_id,dl.name\n" +
                "\t\t ) AS subquery;", companyId);
        Integer recordCount = MyBatisManager.getInstance().executeInt(sqlCount);
        ListInfo listInfo = this.createListInfo();
        listInfo.setDataList(jResult);
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
