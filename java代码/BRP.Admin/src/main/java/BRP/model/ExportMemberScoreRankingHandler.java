package BRP.model;

import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.model.ExportContext;
import strosoft.sys.model.ExportHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ExportMemberScoreRankingHandler extends ExportHandler {
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
        return alData;
    }
}
