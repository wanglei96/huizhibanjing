package strosoft.cms.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.w3c.dom.Node;

import strosoft.app.common.DetailPageMode;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.cms.model.CmsBuildManager;
import strosoft.cms.model.CmsBuildTaskMonitorThread;

/*
 * 添加服务处理类
 */
public class GetCmsBuildProgressServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		Thread.sleep(500);
		int size = CmsBuildManager.getInstance().getLstCmsBuildTask().size();
		int threadSize = CmsBuildTaskMonitorThread.cmsBuildTaskThreadlList.size();
		Boolean building = CmsBuildManager.getInstance().getBuilding();
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("size", String.valueOf(size+threadSize));
		result.put("building", building.toString());
		this.writeSuccessResponse(response, result);

	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");

		JSONObject jData = JSONObject.fromObject(data);

		Node configNode = this.getConfigNode();
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response, DetailPageMode.Add);
		// 添加必填字段
		ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator,
				configNode, jData);
		// 添加唯一字段
		ServiceHandlerHelper.addUniqueFields(theServiceHandlerValidator,
				configNode, jData);
		Boolean isValid = theServiceHandlerValidator.check();

		return isValid;
	}

}
