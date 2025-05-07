package strosoft.cg.service;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;


import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.MySqlHelper;
import strosoft.cg.data.CgProjectInfo;
import strosoft.cg.model.CreateCodeManager;
import strosoft.cg.model.CreateCodeThread;

/*
 * 添加服务处理类
 */
public class ProjectCodeGenerateServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
			Integer projectId = jData.getInt("projectId");
			String sql = "SELECT configuration FROM sys_build_project where id="+projectId;
			ArrayList<String> projects =MyBatisManager.getInstance().getStringList(sqlSession,sql);
			CgProjectInfo project = CreateCodeManager.getInstance().getProjectByJDataWhenGenenrateCode(jData,projects.get(0));
			CreateCodeThread thread = new CreateCodeThread();
			thread.setProject(project);
			thread.run();
			this.writeSuccessResponse(response,"开始生成");
		} catch (Exception e){
			e.printStackTrace();
			this.writeErrorResponse(response, "生成失败");
		} finally {
			sqlSession.close();
		}
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
