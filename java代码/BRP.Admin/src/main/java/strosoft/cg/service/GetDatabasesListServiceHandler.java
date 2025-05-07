package strosoft.cg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;


import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.MySqlHelper;

/*
 * 添加服务处理类
 */
public class GetDatabasesListServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			String sql = "SHOW DATABASES";
			ArrayList<String> databases =MyBatisManager.getInstance().getStringList(sqlSession,sql);
			ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>(); 
			for(int i=0;i<databases.size();i++){
				Map<String, String> map = new HashMap<String, String>();
				map.put("databaseName", databases.get(i));
				result.add(map);
			}
			sqlSession.commit();
			this.writeSuccessResponse(response,result);
		} catch (Exception e){
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			if(!(null==sqlSession)){
				sqlSession.close();
			}
		}

	}
}
