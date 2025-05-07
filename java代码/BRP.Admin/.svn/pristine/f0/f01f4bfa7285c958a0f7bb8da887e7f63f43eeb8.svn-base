package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.ClassHelper;
import strosoft.app.util.FileHelper;
import strosoft.app.util.XmlDocumentHelper;
import strosoft.cms.data.CmsTemplateInfo;
import strosoft.cms.model.CmsTemplateManager;

public class GetCmsTemplateServiceHandler extends ServiceHandler {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String dataManagerType = XmlDocumentHelper.getAttribute(this.getConfigNode(), "DataManagerType");
		CmsTemplateManager dataManager = (CmsTemplateManager) ClassHelper.newInstance(dataManagerType);
		int id = jData.getInt("id");
		CmsTemplateInfo cmsTemplate = (CmsTemplateInfo) ClassHelper.copyObject(CmsTemplateInfo.class,
				dataManager.getEntity(id));
		String destDirName = SysConfig.getRootPath();
		String path = cmsTemplate.getPath();
		String filePath = destDirName + path;
		String encoding = cmsTemplate.getEncode();
		String cmsTemplateContent = FileHelper.readAllText(filePath, encoding);
		cmsTemplate.setCmsTemplateContent(cmsTemplateContent);
		this.writeSuccessResponse(response, cmsTemplate);

	}

}
