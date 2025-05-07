package BRP.service;

import BRP.data.ViewBookSkuInfo;
import BRP.model.BookSkuManager;
import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysUploadFile;
import strosoft.sys.model.SysUploadFileManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UploadBookExcelServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer uploadId = JsonHelper.getInt(jData, "uploadId");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        SysUploadFile theSysUploadFile = SysUploadFileManager.getInstance().getEntity(uploadId);
        String path = SysConfig.getResourceDirectoryPath() + "/" + theSysUploadFile.getPath();
        List<ViewBookSkuInfo> viewBookSkuInfoList = BookSkuManager.getInstance().batchUploadBookExcel(path, companyId);
        this.writeSuccessResponse(response, viewBookSkuInfoList);
    }
}
