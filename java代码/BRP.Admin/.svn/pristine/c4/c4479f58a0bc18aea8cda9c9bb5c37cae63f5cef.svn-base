package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMemberCardBatchNoServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String sql = "select MAX(batch_no) from member_card_batch where company_id = " + companyId;
        String batchNo = MyBatisManager.getInstance().executeString(sql);
        if(StringHelper.isNullOrEmpty(batchNo)){
            batchNo = "001";
            this.writeSuccessResponse(response, batchNo);
            return;
        }
        Integer batchNoValue = Integer.valueOf(batchNo) + 1;
        if(batchNoValue.toString().contains("4")){
            batchNoValue+=1;
        }
        String str = String.format("%0" + batchNo.length() + "d",batchNoValue);
        this.writeSuccessResponse(response, str);
    }
}
