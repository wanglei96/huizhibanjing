package BRP.service;

import BRP.model.ViewMemberCardManager;
import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysUploadFile;
import strosoft.sys.model.SysUploadFileManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRecommendMemberCardInfoListByExcelServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer uploadId = JsonHelper.getInt(jData, "uploadId");
        SysUploadFile theSysUploadFile = SysUploadFileManager.getInstance().getEntity(uploadId);
        String path = SysConfig.getResourceDirectoryPath() + "/" + theSysUploadFile.getPath();

        JSONObject result = ViewMemberCardManager.getInstance().getRecommendMemberCardInfoByFile(path);

        writeSuccessResponse(response, result);
    }
}
