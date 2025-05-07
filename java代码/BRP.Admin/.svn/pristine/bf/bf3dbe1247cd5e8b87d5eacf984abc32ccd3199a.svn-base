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

public class GetMemberScoreRankingServiceHandler extends GetListServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String cardNo = JsonHelper.getString(jData, "cardNo");
        String memberName = JsonHelper.getString(jData, "memberName");
        String createTimeBegin = JsonHelper.getString(jData, "createTimeBegin");
        String createTimeEnd = JsonHelper.getString(jData, "createTimeEnd");
        if(!StringHelper.isNullOrEmpty(createTimeEnd)){
            createTimeEnd+= " 23:59:59";
        }
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        String orderBy = JsonHelper.getString(jData, "orderBy");
        ArrayList<LinkedHashMap<String, Object>> alData  = MemberManager.getInstance().getMemberScoreRanking(companyId, cardNo, memberName, createTimeBegin, createTimeEnd, orderBy, pageIndex, pageSize);

        JSONArray lstMemberCardInfo = JsonHelper.toJSONArray(alData);

        String sqlCount = String.format("SELECT COUNT(*) AS count FROM view_member where member_card_card_no is not null AND company_id=%d" +
                " %s %s" ,
                companyId,
                (!StringHelper.isNullOrEmpty(cardNo)) ? "AND member_card_card_no LIKE '%" + cardNo + "%'" : "",
                (!StringHelper.isNullOrEmpty(memberName)) ? "AND name LIKE '%" + memberName + "%'" : "");
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
