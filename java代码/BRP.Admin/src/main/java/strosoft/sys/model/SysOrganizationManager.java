package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.ArrayListHelper;
import strosoft.sys.data.SysOrganization;



public class SysOrganizationManager extends IdDataManager{
	private static SysOrganizationManager _instance;
	
	public static SysOrganizationManager getInstance(){
		if(_instance == null){
			_instance = new SysOrganizationManager();
		}
		return _instance;
	}
	private SysOrganizationManager(){
		this.setTableName("sys_organization");
		this.setTypeName("SysOrganization");
	}
	/*
	 * 检查登录账号是否存在
	 */
	public Boolean existsLoginName(String loginName)
			throws Exception {
		return MyBatisManager.getInstance().exists(this.getTableName(), "name", loginName);
	}
	/*
	 * 取得实体
	 */
	public SysOrganization getEntity(int sysOrganizationID) throws Exception {
		return (SysOrganization) super.getDataEntity(sysOrganizationID);
	}
	//获取列表
	@SuppressWarnings("unchecked")
	public ArrayList<SysOrganization> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}
	/**
	 * 根据ID删除
	 */
	@SuppressWarnings("null")
	public int delete(int id) throws Exception {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(id);
			batchDeleteByIds(sqlSession, ids);
			sqlSession.commit();
		} catch (IOException e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		return result;
	}
	/**
	 * 根据ID删除
	 */
	@SuppressWarnings("null")
	public int batchDelete(List<Integer> ids) throws Exception {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			batchDeleteByIds(sqlSession, ids);
			sqlSession.commit();
		} catch (IOException e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		return result;
	}
	/**
	 * 根据ID删除
	 */
	@SuppressWarnings("null")
	public int batchDeleteByIds(SqlSession sqlSession,List<Integer> ids) throws Exception {
		int result = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(ids);
		List<Integer> resultList = ids;
		while(resultList.size()>0){
			resultList = getSubSysOrganizationId(sqlSession, resultList);
			list.addAll(resultList);
		}
		//删除组织下的成员
		result += SysUserManager.getInstance().deleteBySysOrganizationIdList(sqlSession, list);
		//删除组织
		String idListStr = ArrayListHelper.intListToString(list, ",");
		String sql = "delete from sys_organization where id in ("+idListStr+")";
		result += sqlSession.delete("SysOrganization.deleteBysql", sql);
		return result;
	}
	public List<Integer> getSubSysOrganizationId(SqlSession sqlSession,List<Integer> ids){
		String idStr= ArrayListHelper.intListToString(ids, ",");
		if(idStr.equals("")){
			return new ArrayList<Integer>();
		}
		String sql = "select id from sys_organization where parent_id in ("+idStr+")";
		List<Integer> subOrganizations = sqlSession.selectList("SysOrganization.selectBysql", sql);
			return subOrganizations;
		}
}
