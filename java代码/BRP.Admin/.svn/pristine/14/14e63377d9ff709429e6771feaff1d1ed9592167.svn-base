package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import strosoft.app.common.CodeDataManager;
import strosoft.sys.data.SysMenuType;



public class SysMenuTypeManager extends CodeDataManager{
	private static SysMenuTypeManager _instance;
	
	public static SysMenuTypeManager getInstance(){
		if(_instance == null){
			_instance = new SysMenuTypeManager();
		}
		return _instance;
	}
	
	private SysMenuTypeManager(){
		this.setTableName("sys_menu_type");
		this.setTypeName("SysMenuType");
	}
	/*
	 * 取得实体
	 */
	public SysMenuType getEntity(String sysMenuTypeID) throws Exception {
		return (SysMenuType) super.getDataEntity(sysMenuTypeID);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<SysMenuType> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}
}
