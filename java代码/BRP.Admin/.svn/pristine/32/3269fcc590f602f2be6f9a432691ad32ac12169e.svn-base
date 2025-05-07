package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import net.sf.json.JSONObject;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.SysConfig;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.ViewCmsChannel;
import strosoft.cms.model.ViewCmsChannelManager;

/*
 * 根据栏目编码取得栏目视图对象
 */
public class GetViewCmsChannelByCodeServiceHandler extends ServiceHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String code = JsonHelper.getString(jData, "code", "Code");
		ViewCmsChannel theViewCmsChannel = ViewCmsChannelManager.getInstance().getViewCmsChannelByCode(code);

		/*String text = ServiceHandlerHelper.replaceImageUrl(theViewCmsChannel.getText(),
				SysConfig.getUeditorImagePrefixList());
		theViewCmsChannel.setText(text);*/
		this.writeSuccessResponse(response, theViewCmsChannel);
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		Node configNode = this.getConfigNode();
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response, DetailPageMode.View);
		theServiceHandlerValidator.addRequiredItem(jData, "栏目编码", "code");
		// 添加必填字段
		ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator, configNode, jData);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}

}
