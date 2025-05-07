package strosoft.cg.service;

import java.io.File;
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
import strosoft.app.util.FileHelper;
import strosoft.app.util.MySqlHelper;
import strosoft.cg.data.CgProjectInfo;
import strosoft.cg.model.CreateCodeManager;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.model.CmsTemplateManager;

/*
 * 添加服务处理类
 */
public class UpdateProjectServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		SqlSession sqlSession = null;
		JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
		CgProjectInfo project = (CgProjectInfo) ServiceHandlerHelper
				.getDataEntity(request, this.getConfigNode(), null, false);
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			String sql = "SELECT configuration FROM sys_build_project where id="+project.getProjectId();
			ArrayList<String> projects =MyBatisManager.getInstance().getStringList(sqlSession,sql);
			String configuration = CreateCodeManager.getInstance().getConfigurationWhenUpdateProject(project,projects.get(0)).replace("\\", "\\\\");
			System.out.println(configuration);
			boolean isXMLFormat = CreateCodeManager.getInstance().checkXMLFormat(configuration);
			if(isXMLFormat){
				//将数据插入数据库
				sql = "update sys_build_project set name='"+project.getProjectName()+"',configuration='"+configuration+"' where id="+project.getProjectId();
				sqlSession.update("CgProject.updateBysql", sql);
				sqlSession.commit();
				this.writeSuccessResponse(response,project);
			}else{
				this.writeErrorResponse(response, "内容不是XML格式!");
				sqlSession.commit();
			}
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
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
