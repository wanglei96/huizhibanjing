package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.model.ExportContext;
import strosoft.sys.model.ExportManager;

/*
 * 导出服务处理类
 */
public class ExportServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jData = this.getRequestData(request);
		String exportName = JsonHelper.getString(jData, "exportName");
		ExportContext context = new ExportContext();
		context.setExportName(exportName);
		context.setParameters(jData);
		context.setRequest(request);
		ExportManager.getInstance().export(context);
		JSONObject jResult = new JSONObject();
		jResult.put("fileName", context.getFileName());
		jResult.put("filePath", context.getFilePath());
		this.writeSuccessResponse(response, jResult);
	}
}
