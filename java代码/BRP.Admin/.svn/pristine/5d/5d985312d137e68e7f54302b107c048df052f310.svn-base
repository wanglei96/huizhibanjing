package strosoft.sys.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.*;
import strosoft.sys.data.SysOrganization;
import strosoft.sys.model.SysBasicTableManager;
import strosoft.sys.model.SysMenuManager;
import strosoft.sys.model.SysOrganizationManager;
import net.sf.json.*;

/*
 * 批量删除服务处理类
 */
public class BatchDeleteSysOrganizationServiceHandler extends ServiceHandler {

	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		List<Integer> lstId = JsonHelper.getIntArray(jData, "primaryKeys",
				"ids");
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			SysOrganizationManager.getInstance().batchDeleteByIds(sqlSession, lstId);
			sqlSession.commit();
		}catch(Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		this.writeSuccessResponse(response);
	}
}
