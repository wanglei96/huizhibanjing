package strosoft.app.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import strosoft.app.common.EncryptManager;
import strosoft.app.model.CodeNameTableManager;
import strosoft.app.model.IdNameTableManager;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;

/*
 * 添加服务处理类
 */
public class MoveServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		String tableName = JsonHelper.getString(jData, "tableName");
		String moveDirection = JsonHelper.getString(jData, "moveDirection");
		String primaryKeyName = JsonHelper.getString(jData, "primaryKeyName");
		if(primaryKeyName.equals("id")){
			int id = JsonHelper.getInt(jData, "primaryKey");
			String condition = JsonHelper.getString(jData, "condition");
			IdNameTableManager.getInstance().move(tableName, moveDirection, id, condition);
		}
		else if(primaryKeyName.equals("code")){
			String code = JsonHelper.getString(jData, "primaryKey");
			String condition = JsonHelper.getString(jData, "condition");
			CodeNameTableManager.getInstance().move(tableName, moveDirection, code, condition);
		}
		this.writeSuccessResponse(response);

	}


	/*
	 * 检查输入
	 */
	private Boolean checkInput(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		ServiceHandlerValidator theServiceHandlerValidator = new ServiceHandlerValidator(
				response);
		theServiceHandlerValidator.addRequiredItem(jData, "表名", "tableName");
		theServiceHandlerValidator.addRequiredItem(jData, "移动方向", "moveDirection");
		theServiceHandlerValidator.addRequiredItem(jData, "主键", "primaryKey");
		theServiceHandlerValidator.addRequiredItem(jData, "主键名称", "primaryKeyName");
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
