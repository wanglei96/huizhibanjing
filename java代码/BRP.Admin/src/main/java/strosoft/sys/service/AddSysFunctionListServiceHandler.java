package strosoft.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;
import strosoft.app.common.TableDataMaster;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysFunction;
import strosoft.sys.model.SysRoleCmsChannelPermissionManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AddSysFunctionListServiceHandler extends ServiceHandler{
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TableDataMaster dataManager = (TableDataMaster) ServiceHandlerHelper
				.getDataManager(this.getConfigNode());
		if (dataManager == null) {
			this.writeErrorResponse(response, "数据管理类配置错误！");
			return;
		}
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			String data = request.getParameter("data");
			JSONObject jData = JSONObject.fromObject(data);
			JSONArray functionList = jData.getJSONArray("functionList");
			for(int i=0;i<functionList.size();i++){
				JSONObject function = functionList.getJSONObject(i);
				String code = function.getString("code");
				String name = function.getString("name");
				String resource = function.getString("resource");
				SysFunction sysFunction = new SysFunction();
				sysFunction.setCode(code);
				sysFunction.setName(name);
				sysFunction.setDisplayOrder(0);
				sysFunction.setResource(resource);
				dataManager.add(sqlSession,sysFunction);
			}
			sqlSession.commit();
			this.writeSuccessResponse(response);
		}catch (Exception e) {
			sqlSession.rollback();
			this.writeErrorResponse(response, "添加失败");
		}finally{
			sqlSession.close();
		}
	}
}
