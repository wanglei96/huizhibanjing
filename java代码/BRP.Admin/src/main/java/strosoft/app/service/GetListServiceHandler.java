package strosoft.app.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.model.ListInfo;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.XmlDocumentHelper;

/*
 * 取得数据表
 */
public class GetListServiceHandler extends ServiceHandler {

    private GetListServiceHandlerContext context = null;

    public GetListServiceHandlerContext getContext() {
        return context;
    }

    public void setContext(GetListServiceHandlerContext context) {
        this.context = context;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Boolean isValid = this.checkInput(request, response);
        if (!isValid) {
            return;
        }
        // 从request中获取参数
        this.context = this.getContext(request);
        // 创建返回结果集
        ListInfo listInfo = this.createListInfo();
        // 查询数据列表
        ArrayList<LinkedHashMap<String, Object>> alData = ServiceHandlerHelper.getDataList(context);
        listInfo.setDataList(alData);
        // 当前页
        listInfo.setPageIndex(context.getPageIndex());
        // 分页大小
        listInfo.setPageSize(context.getPageSize());
        // 记录数
        listInfo.setRecordCount(context.getRecordCount());
        // 页数
        listInfo.setPageCount(context.getPageCount());
        this.processListInfo(listInfo);
        this.writeSuccessResponse(response, listInfo);

    }

    /*
     * 检查输入
     */
    protected Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GetListServiceHandlerContext context = this.getContext(request);
        String tableName = context.getTableName();
        if (tableName == null || tableName.equals("")) {
            this.writeErrorResponse(response, "表名不能为空！");
            return false;
        }
        return true;
    }

    /*
     * 取得列表信息
     */
    protected ListInfo createListInfo() {
        return new ListInfo();
    }

    /*
     * 处理数据
     */
    protected void processListInfo(ListInfo listInfo) throws Exception {
        ArrayList<LinkedHashMap<String, Object>> alData = (ArrayList<LinkedHashMap<String, Object>>) listInfo
                .getDataList();
        Node configNode = this.getConfigNode();
        String encryptFields = XmlDocumentHelper.getAttribute(configNode, "EncryptFields");
        for (LinkedHashMap<String, Object> currentDataItem : alData) {
            LinkedHashMap<String, Object> lhmTemp = new LinkedHashMap<String, Object>();
            Iterator<Entry<String, Object>> iterator = currentDataItem.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> currentEntry = iterator.next();
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
                this.context.getPropertyNameType());
        listInfo.setDataList(jarrData);
    }

    /*
     * 取得参数
     */
    protected GetListServiceHandlerContext getContext(HttpServletRequest request) throws Exception {
        Node configNode = this.getConfigNode();
        String condition = this.getCondition(request);
        GetListServiceHandlerContext context = ServiceHandlerHelper.getGetListServiceHandlerContext(request, configNode,
                condition);
        return context;
    }

    /*
     * 取得查询条件
     */
    protected String getCondition(HttpServletRequest request) throws Exception {
        Node configNode = this.getConfigNode();
        String condition = ServiceHandlerHelper.getGetListCondition(request, configNode);
        return condition;
    }

}
