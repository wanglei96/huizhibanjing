package BRP.service;

import BRP.data.MemberCardInfo;
import BRP.model.MemberManager;
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
import java.util.List;

public class GetMemberBorrowBookTotalServiceHandler extends GetListServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String cardNo = JsonHelper.getString(jData, "cardNo");
        String memberName = JsonHelper.getString(jData, "memberName");
        String mobilePhone = JsonHelper.getString(jData, "mobilePhone");
        String createTimeBegin = JsonHelper.getString(jData, "createTimeBegin");
        String createTimeEnd = JsonHelper.getString(jData, "createTimeEnd");
        if(!StringHelper.isNullOrEmpty(createTimeEnd)){
            createTimeEnd+= " 23:59:59";
        }
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        String orderBy = JsonHelper.getString(jData, "orderBy");
        ArrayList<LinkedHashMap<String, Object>> alData  = MemberManager.getInstance().getMemberBorrowBookTotal(companyId, cardNo, memberName, mobilePhone, createTimeBegin, createTimeEnd, orderBy, pageIndex, pageSize);

        List<MemberCardInfo> lstMemberCardInfo =  JsonHelper.toJavaList(MemberCardInfo.class, alData);
        String sqlCount = "SELECT COUNT(*) AS count FROM member_card where disabled IS NOT TRUE AND member_id IS NOT NULL AND company_id=" + companyId;
        Integer recordCount = MyBatisManager.getInstance().executeInt(sqlCount);
        ListInfo listInfo = this.createListInfo();
        listInfo.setDataList(lstMemberCardInfo);
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
