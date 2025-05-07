package BRP.model;

import net.sf.json.JSONObject;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.model.ExportContext;
import strosoft.sys.model.ExportHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class ExportDelivererTotalHandler extends ExportHandler {
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
        String deliverLineName = JsonHelper.getString(jData, "deliverLineName");
        String name = JsonHelper.getString(jData, "delivererName");
        String deliverTimeBegin = JsonHelper.getString(jData, "deliverTimeBegin");
        String deliverTimeEnd = JsonHelper.getString(jData, "deliverTimeEnd");
        if(!StringHelper.isNullOrEmpty(deliverTimeEnd)){
            deliverTimeEnd+= " 23:59:59";
        }
        Integer pageIndex = JsonHelper.getInt(jData, "pageIndex");
        Integer pageSize = JsonHelper.getInt(jData, "pageSize");
        String orderBy = JsonHelper.getString(jData, "orderBy");

        //获得数据
        ArrayList<LinkedHashMap<String, Object>> alData = CompanyUserManager.getInstance().getDelivererTotalData(companyId, deliverLineName, name, deliverTimeBegin, deliverTimeEnd, pageIndex, pageSize, orderBy);

        return alData;
    }
}
