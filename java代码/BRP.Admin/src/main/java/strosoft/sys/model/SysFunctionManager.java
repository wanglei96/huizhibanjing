package strosoft.sys.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;


import strosoft.app.common.CodeDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.sys.data.SysFunction;
import strosoft.sys.data.SysFunctionMaster;

public class SysFunctionManager extends SysFunctionMaster {
	private static SysFunctionManager _instance;
	
	public static SysFunctionManager getInstance(){
		if(_instance == null){
			_instance = new SysFunctionManager();
		}
		return _instance;
	}
	private SysFunctionManager(){
		this.setTableName("sys_function");
		this.setTypeName("SysFunction");
	}
	/*
	 * 取得实体 
	 */
	public SysFunction getEntity(String SysFunctionID) throws Exception{		
		return (SysFunction)super.getDataEntity(SysFunctionID);
	}
	/*
	 *取得列表
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SysFunction> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}
	/*
	 * 删除
	 */
	public int delete(String code) throws Exception {
		SqlSession sqlSession = null;
		int result = 0;
		try{
			sqlSession =  MyBatisManager.getInstance().openSession();
			String sql = String.format("delete from sys_role_permission where permission='%s'",
					code);
			sqlSession.delete("SysRolePermission.deleteSysRolePermissionBySql", sql);
			sql = String.format("delete from sys_function where code='%s'",
					code);
			result = sqlSession.delete("SysFunction.deleteSysFunctionBySql", sql);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		return result;
	}
	/*
	 * 删除
	 */
	public int delete(SqlSession sqlSession,String code) {
		int result = 0;
		String sql = String.format("delete from sys_role_permission where permission='%s'",
				code);
		sqlSession.delete("SysRolePermission.deleteSysRolePermissionBySql", sql);
		sql = String.format("delete from sys_function where code='%s'",
				code);
		result = sqlSession.delete("SysFunction.deleteSysFunctionBySql", sql);
		return result;
	}
	/*
	 * 批量删除
	 */
	public int batchDeleteByCodes(List<String> jarrCode) throws Exception {
		SqlSession sqlSession = null;
		int result = 0;
		try{
			sqlSession =  MyBatisManager.getInstance().openSession();
			StringBuffer sbCode = new StringBuffer();
			for (String code : jarrCode) {
				if (sbCode.length() > 0) {
					sbCode.append("','");
				}
				sbCode.append(code);
			}
			String strCodes = "'" + sbCode.toString() + "'";
			String sql = String.format("delete from sys_role_permission where permission in (%s)",
					strCodes);
			sqlSession.delete("SysRolePermission.deleteSysRolePermissionBySql", sql);
			sql = String.format("delete from sys_function where code in (%s)", strCodes);
			result = sqlSession.delete("SysFunction.deleteSysFunctionBySql", sql);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		return result;
	}
	/*
	 * 批量删除
	 */
	public int batchDeleteByCodes(SqlSession sqlSession,List<String> jarrCode){
		if(jarrCode == null || jarrCode.size()<1){
			return 0;
		}
		int result = 0;
		SysRolePermissionManager.getInstance().deleteSysRolePermissionsByPermissions(sqlSession, jarrCode);
		String sql = String.format("delete from sys_function where code in (%s)", stringListToString(jarrCode));
		sqlSession.delete("SysFunction.deleteSysFunctionBySql", sql);
		return result;
	}
	
	/**
	 * 
	 * @param sqlSession
	 * @param resources
	 * @return
	 */
	public int batchDeleteByResource(SqlSession sqlSession,List<String> resources){
		int result = 0;
		if(null == resources || resources.size()<1){
			return 0;
		}
		String sql = "select code from sys_function where resource in ("+stringListToString(resources)+")";
		List<String> codes = sqlSession.selectList("SysFunction.selectStringBySql", sql);
		result = batchDeleteByCodes(sqlSession,codes);
		return result;
	}
	
	public String stringListToString(List<String> list){
		StringBuffer sb = new StringBuffer();
		if(null == list || list.size()<1){
			return "";
		}
		sb.append("'");
		sb.append(list.get(0));
		for(int i=1;i<list.size();i++){
			sb.append("','");
			sb.append(list.get(i));
		}
		sb.append("'");
		return sb.toString();
	}
}
