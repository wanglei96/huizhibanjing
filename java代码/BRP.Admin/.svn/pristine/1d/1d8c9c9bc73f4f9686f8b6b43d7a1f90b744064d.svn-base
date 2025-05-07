package strosoft.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;

/*
 * 添加服务处理类
 */
public class MoveCmsServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jData = JSONObject.fromObject(request.getParameter("data"));
		String moveDirection = jData.getString("moveDirection");
		String tableName = jData.getString("tableName");
		Integer id = jData.getInt("id");
		String condition = jData.getString("condition");
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisManager.getInstance().openSession();
			String sql = "select display_order from "+tableName+" where id="+id;
			Integer displayOrder =sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
			sql = "select count(id) from "+tableName+" where "+condition;
			Integer totalNum =sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
			if(moveDirection.equals("MoveUp")){
				if(displayOrder<2){
					return;
				}
				sql = "select id from "+tableName+" where display_order = "+(displayOrder-1)+" and "+
						condition+" limit 1";
				Integer frontId = sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
				if(null != frontId){
					sql = "UPDATE "+tableName+" SET display_order="+displayOrder+" where id ="+frontId;
					sqlSession.update("CmsChannel.updateBysql", sql);
				}
				sql = "UPDATE "+tableName+" SET display_order="+(displayOrder-1)+" where id ="+id;
				sqlSession.update("CmsChannel.updateBysql", sql);
			}
			if(moveDirection.equals("MoveDown")){
				if(displayOrder>totalNum){
					return;
				}
				sql = "select id from "+tableName+" where display_order = "+(displayOrder+1)+" and "+
						condition+" limit 1";
				Integer nextId = sqlSession.selectOne("CmsChannel.selectIntBySql", sql);
				if(null != nextId){
					sql = "UPDATE "+tableName+" SET display_order="+displayOrder+" where id ="+nextId;
					sqlSession.update("CmsChannel.updateBysql", sql);
					sql = "UPDATE "+tableName+" SET display_order="+(displayOrder+1)+" where id ="+id;
					sqlSession.update("CmsChannel.updateBysql", sql);
				}
			}
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		this.writeSuccessResponse(response);

	}
}
