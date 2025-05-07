package strosoft.cms.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Node;

import strosoft.app.common.TableDataMaster;
import strosoft.app.common.DetailPageMode;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.service.ServiceHandlerValidator;
import strosoft.app.util.JsonHelper;
import strosoft.cms.data.CmsChannel;
import strosoft.cms.data.CmsChannelChannelGroup;
import strosoft.cms.data.CmsContent;
import strosoft.cms.model.CmsChannelManager;
import strosoft.cms.model.CmsContentManager;
import strosoft.sys.data.SysBasicTable;
import strosoft.sys.model.SysBasicTableManager;

/*
 * 添加服务处理类
 */
public class DeleteCmsContentServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int id = JsonHelper.getInt(jData, "primaryKey", "id");
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			int deleteID = CmsContentManager.getInstance().delete(sqlSession,id);
			this.writeSuccessResponse(response,deleteID);
			sqlSession.commit();
		} catch (Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		} finally{
			sqlSession.close();
		}
	}
}
