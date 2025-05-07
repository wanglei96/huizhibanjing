package strosoft.cms.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.cms.model.CmsContentManager;
import strosoft.cms.model.CmsTemplateManager;

public class BatchDeleteCmsTemplatetServiceHandler extends ServiceHandler {
	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");
		// System.out.println(data);
		JSONObject jData = JSONObject.fromObject(data);
			List<Integer> lstPrimaryKey = JsonHelper.getIntArray(jData,
					"primaryKeys");
		CmsTemplateManager.getInstance().batchDelete(lstPrimaryKey);
		this.writeSuccessResponse(response);
	}
}
