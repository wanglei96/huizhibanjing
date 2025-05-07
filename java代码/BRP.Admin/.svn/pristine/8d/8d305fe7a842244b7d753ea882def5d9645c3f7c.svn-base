package strosoft.app.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.DetailPageMode;
import strosoft.app.model.CustomTableTableManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;
import strosoft.sys.model.SysBasicTableFieldManager;

/*
 * 添加自定义基础表服务处理类
 */
public class AddCustomTableServiceHandler extends ServiceHandler {
	//该自定义基础表所有字段
	private List<SysBasicTableField> sysBasicTableFields;
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		//获取自定义表每个字段的值，拼接为插入sql
		int result = CustomTableTableManager.getInstance().add(jData, sysBasicTableFields);
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
				response, DetailPageMode.Add);
		//获取所有字段信息
		String condition = "table_name = '"+tableName+"'";
		sysBasicTableFields = SysBasicTableFieldManager.getInstance().getList(condition);
		//添加检查字段
		CustomTableTableManager.getInstance().setCheckField(jData, theServiceHandlerValidator, sysBasicTableFields);
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
