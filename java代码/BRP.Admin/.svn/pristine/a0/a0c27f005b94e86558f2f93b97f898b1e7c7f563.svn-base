package strosoft.app.service;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import strosoft.app.util.ClassHelper;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.app.util.XmlDocumentHelper;

/**
 * 根据外键取得实体
 * 
 * @author Admin
 *
 */
public class GetEntityByUniqueKeyServiceHandler extends ServiceHandler {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取request参数字符串
		String data = request.getParameter("data");
		// 将参数字符串转为JSONObject类型
		JSONObject jData = JSONObject.fromObject(data);
		// 根据服务处理类参数获取对应的数据库访问类（Mananger类）
		String dataManagerType = XmlDocumentHelper.getAttribute(this.getConfigNode(), "DataManagerType");
		Object dataManager = ClassHelper.newInstance(dataManagerType);
		String uniqueKeyFieldName = XmlDocumentHelper.getAttribute(this.getConfigNode(), "UniqueKeyFieldName");
		String methodName = "getEntityBy" + StringHelper.toPascal(uniqueKeyFieldName);

		// 参数名称
		String parameterName = StringHelper.toCamelCase(uniqueKeyFieldName);
		Object uniqueKey = JsonHelper.getObject(jData, parameterName);
		Method[] methods = dataManager.getClass().getMethods();

		Object objEntity = null;
		for (Method currentMethod : methods) {
			if (currentMethod.getName().equals(methodName)) {
				if (currentMethod.getParameterCount() == 1) {
					objEntity = currentMethod.invoke(dataManager, uniqueKey);
					break;
				}
			}
		}
		com.alibaba.fastjson2.JSONObject jResponseData = JsonHelper.toFastJSONObject(objEntity);
		this.writeSuccessResponse(response, jResponseData);
	}

}
