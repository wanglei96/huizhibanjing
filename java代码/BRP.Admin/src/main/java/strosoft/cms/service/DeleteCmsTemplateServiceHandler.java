package strosoft.cms.service;

import java.io.File;
import java.util.logging.FileHandler;

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
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.CmsTemplate;
import strosoft.cms.model.CmsChannelManager;
import strosoft.cms.model.CmsTemplateManager;


/*
 * 添加服务处理类
 */
public class DeleteCmsTemplateServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int id = JsonHelper.getInt(jData, "primaryKey", "id");
		System.out.println("id"+id);
		int deleteNum = 0;
		SqlSession sqlSession = MyBatisManager.getInstance().openSession();
		try{
			deleteNum  = CmsTemplateManager.getInstance().deleteOne(sqlSession,id);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		this.writeSuccessResponse(response,deleteNum);
	}
}
