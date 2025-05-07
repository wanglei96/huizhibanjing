package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.cms.model.CmsChannelManager;

/*
 * 删除栏目服务处理类
 */
public class DeleteCmsChannelServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jData = this.getRequestData(request);
		int id = JsonHelper.getInt(jData, "primaryKey", "id");
		int result = CmsChannelManager.getInstance().deleteCmsChannel(id);
		this.writeSuccessResponse(response, result);
	}
}
