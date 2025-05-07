package BRP.service;

import BRP.data.ViewMemberCard;
import BRP.model.ViewMemberCardManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysUploadFile;
import strosoft.sys.model.SysUploadFileManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetMemberCardListByExcelServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer uploadId = JsonHelper.getInt(jData, "uploadId");
        SysUploadFile theSysUploadFile = SysUploadFileManager.getInstance().getEntity(uploadId);
        String path = SysConfig.getResourceDirectoryPath() + "/" + theSysUploadFile.getPath();
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        ArrayList<String> cardNoList = ViewMemberCardManager.getInstance().getCardNoLisByFile(path);
        List<ViewMemberCard> memberCardListByExcel = ViewMemberCardManager.getInstance().getViewMemberCardListByCardNoLis(cardNoList, companyId);
        List<JSONObject> exceptionMemberCardList = new ArrayList<>();

        for (String cardNo : cardNoList) {
            boolean isHave = false;
            for (ViewMemberCard memberCard : memberCardListByExcel) {
                if (memberCard.getCardNo().equals(cardNo)) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("cardNo", cardNo);
                exceptionMemberCardList.add(jsonObject);
            }
        }

        JSONArray memberCardListByExcelArr = JsonHelper.toJsonArray(memberCardListByExcel);
        JSONArray exceptionMemberCardListArr = JsonHelper.toJsonArray(exceptionMemberCardList);
        JSONObject resultJson = new JSONObject();
        resultJson.put("memberCardListByExcel", memberCardListByExcelArr);
        resultJson.put("exceptionMemberCardList", exceptionMemberCardListArr);

        writeSuccessResponse(response, resultJson);
    }
}
