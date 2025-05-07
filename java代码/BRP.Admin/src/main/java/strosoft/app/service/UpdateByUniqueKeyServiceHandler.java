package strosoft.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import net.sf.json.JSONObject;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.TableDataMaster;
import strosoft.app.data.DataEntity;

/**
 * 根据唯一键修改
 * 
 * @author Admin
 *
 */
public class UpdateByUniqueKeyServiceHandler extends ServiceHandler {

	@Override
	@SuppressWarnings("rawtypes")
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 检查页面输入规则（必填项，唯一项）
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			// 不符合输入规则，终止。
			return;
		}
		// 根据服务处理类参数获取对应的数据库访问类（Mananger类）
		TableDataMaster dataManager = ServiceHandlerHelper.getDataManager(this.getConfigNode());
		if (dataManager == null) {
			// 没有获取到数据库访问类，返回错误到页面
			this.writeErrorResponse(response, "数据管理类配置错误！");
			return;
		}
		JSONObject jData = this.getRequestData(request);
		DataEntity newDataEntity = ServiceHandlerHelper.getDataEntityByUniqueKey(jData, this.getConfigNode(),
				dataManager);
		// 设置数据对象值
		ServiceHandlerHelper.setDataEntityByRequestData(newDataEntity, jData, this.getConfigNode());
		// 通过获取到的数据库访问类执行修改操作
		int result = dataManager.update(newDataEntity);
		// 没有获取到数据库访问类，返回错误到页面
		this.writeSuccessResponse(response, result);
	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		Node configNode = this.getConfigNode();
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(response, DetailPageMode.Edit);
		// 添加必填字段
		ServiceHandlerHelper.addRequiredFields(theServiceHandlerValidator, configNode, jData);
		// 添加唯一字段
		ServiceHandlerHelper.addUniqueFields(theServiceHandlerValidator, configNode, jData);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
