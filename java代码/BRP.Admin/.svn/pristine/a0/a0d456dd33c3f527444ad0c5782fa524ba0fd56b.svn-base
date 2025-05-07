package strosoft.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.w3c.dom.Node;

import strosoft.app.common.TableDataMaster;
import strosoft.app.common.DetailPageMode;
import strosoft.app.data.DataEntity;
import strosoft.app.model.CustomTableTableManager;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysBasicTableField;
import strosoft.sys.model.SysBasicTableFieldManager;


public class UpdateCustomTableServiceHandler extends ServiceHandler {
	//该自定义基础表所有字段
	private List<SysBasicTableField> sysBasicTableFields;

	@SuppressWarnings("rawtypes")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//检查页面输入规则（必填项，唯一项）
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			//不符合输入规则，终止。
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		//获取自定义表每个字段的值，拼接为插入sql
		int result = CustomTableTableManager.getInstance().update(jData, sysBasicTableFields);
		this.writeSuccessResponse(response,result);

	}

	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response, DetailPageMode.Edit);

		//获取所有字段信息
		String condition = "table_name = '"+tableName+"'";
		sysBasicTableFields = SysBasicTableFieldManager.getInstance().getList(condition);
		//添加检查字段
		CustomTableTableManager.getInstance().setCheckField(jData, theServiceHandlerValidator, sysBasicTableFields);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
