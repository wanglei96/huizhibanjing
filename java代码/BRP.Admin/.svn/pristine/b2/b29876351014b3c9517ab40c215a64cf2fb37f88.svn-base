package strosoft.sys.service;

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
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.*;

import net.sf.json.JSONObject;

import strosoft.sys.data.*;
import strosoft.sys.model.SysBasicTableFieldManager;

/*
 * 添加服务处理类
 */
public class MoveCustomFieldTableServiceHandler extends ServiceHandler {

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
		String fieldName = JsonHelper.getString(jData, "fieldName");
		SysBasicTableFieldManager.getInstance().move(tableName, moveDirection, fieldName);
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
		theServiceHandlerValidator.addRequiredItem(jData, "字段", "fieldName");
		Boolean isValid = theServiceHandlerValidator.check();
		return isValid;
	}
}
