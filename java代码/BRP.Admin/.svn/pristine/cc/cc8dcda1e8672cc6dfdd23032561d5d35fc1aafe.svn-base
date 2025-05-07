package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.TableDataMaster;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.SysConfig;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.FileHelper;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsSite;
import strosoft.cms.model.CmsSiteManager;

public class AddCmsSiteServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Boolean isValid = this.checkInput(request, response);
		if (!isValid) {
			return;
		}
		
		CmsSite cmsSite = (CmsSite) ServiceHandlerHelper.getDataEntity(request,
				this.getConfigNode(), null, false);
		
		if (cmsSite == null) {
			this.writeErrorResponse(response, "数据实体配置错误！");
			return;
		}
		String path = cmsSite.getPath();
		if(null == path || path.equals("")){
			this.writeErrorResponse(response, "根路径不能为空");
			return;
		}
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		
		try{
			//检查是否有站点重名，或站点根目录重名
			String sql = "select count(id) from cms_site where name = '"+cmsSite.getName()+"'";
			int nameNum = sqlSession.selectOne("CmsSite.selectIntBySql", sql);
			if(nameNum>0){
				this.writeErrorResponse(response, "站点名已存在");
				return;
			}
			sql = "select count(id) from cms_site where path = '"+cmsSite.getPath()+"'";
			int pathNum = sqlSession.selectOne("CmsSite.selectIntBySql", sql);
			if(pathNum>0){
				this.writeErrorResponse(response, "站点根目录已存在");
				return;
			}
			CmsSiteManager.getInstance().add(sqlSession, cmsSite);
			sqlSession.commit();
			this.writeSuccessResponse(response);
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			this.writeErrorResponse(response, "创建站点失败");
		}finally{
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
