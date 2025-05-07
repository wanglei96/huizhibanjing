package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.ClassHelper;
import strosoft.app.util.FileHelper;
import strosoft.cms.data.CmsInclude;
import strosoft.cms.model.CmsIncludeManager;
import net.sf.json.JSONObject;

public class GetCmsIncludeServiceHandler extends ServiceHandler {
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");

		// System.out.println(data);
		JSONObject jData = JSONObject.fromObject(data);
		CmsIncludeManager dataManager = (CmsIncludeManager) ClassHelper
				.newInstance("strosoft.cms.model.CmsIncludeManager");
			int id = jData.getInt("id");
			CmsInclude newEntity = dataManager.getEntity(id);
			String destDirName=SysConfig.getRootPath();;
			String path = newEntity.getPath();
			String filePath = destDirName+path;
			String cmsIncludeContent = FileHelper.readAllText(filePath);
			newEntity.setCmsIncludeContent(cmsIncludeContent);
		this.writeSuccessResponse(response, newEntity);

	}

}
