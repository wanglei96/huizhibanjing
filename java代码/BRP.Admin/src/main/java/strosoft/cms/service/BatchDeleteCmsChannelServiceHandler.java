package strosoft.cms.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.cms.model.CmsChannelManager;

/*
 * 添加服务处理类
 */
public class BatchDeleteCmsChannelServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);//获取idList
		List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys","ids");
		int deleteID = CmsChannelManager.getInstance().batchDeleteCmsChannels(lstId);
		this.writeSuccessResponse(response,deleteID);
	}
}
