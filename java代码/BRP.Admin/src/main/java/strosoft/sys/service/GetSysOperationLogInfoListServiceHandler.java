package strosoft.sys.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.model.ListInfo;
import strosoft.app.service.GetListServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sys.model.SysOperationLogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GetSysOperationLogInfoListServiceHandler extends GetListServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        String sysUserLoginName = JsonHelper.getString(jData, "sysUserLoginName");
        String sysUserTrueName = JsonHelper.getString(jData, "sysUserTrueName");
        String operationTypeCode = JsonHelper.getString(jData, "operationTypeCode");
        String operateTimeBegin = JsonHelper.getString(jData, "operateTimeBegin");
        String operateTimeEnd = JsonHelper.getString(jData, "operateTimeEnd");
        String condition = JsonHelper.getString(jData, "condition");
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        String orderBy = JsonHelper.getString(jData, "orderBy");

        MySqlConditionBuilder conditionBuilder = new MySqlConditionBuilder();
        conditionBuilder.add(condition);
        conditionBuilder.addLikeCondition("sys_user_login_name", sysUserLoginName);
        conditionBuilder.addLikeCondition("sys_user_true_name", sysUserTrueName);
        conditionBuilder.addEqualCondition("operation_type_code", operationTypeCode);
        conditionBuilder.addTimeSpanCondition(operateTimeBegin, operateTimeEnd, "operate_time");
        String sqlCondition = conditionBuilder.toString();

        String sqlWhere = "";
        if (sqlCondition != null && !sqlCondition.equals("")) {
            sqlWhere = " where " + sqlCondition;
        }
        ArrayList<LinkedHashMap<String, Object>> alData =
                SysOperationLogManager.getInstance().getSysOperationLogInfoList(pageIndex, pageSize, orderBy, sqlWhere);
        for (LinkedHashMap<String, Object> map : alData) {
            // 检查 operateTime 是否为 null
            if (map.get("operateTime") != null) {
                // 如果 operateTime 不为 null，则将其转换为字符串格式
                map.put("operateTime", map.get("operateTime").toString());
            }
        }
        String sqlCount = "SELECT COUNT(*) AS count FROM sys_operation_log " + sqlWhere;
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
