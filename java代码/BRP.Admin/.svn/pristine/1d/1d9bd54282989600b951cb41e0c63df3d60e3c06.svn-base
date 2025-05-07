package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import strosoft.app.common.CodeDataManager;
import strosoft.sys.data.SysSettingCategory;

public class SysSettingCategoryManager extends CodeDataManager{
	private static SysSettingCategoryManager _instance;

	public static SysSettingCategoryManager getInstance() {
		if (_instance == null) {
			_instance = new SysSettingCategoryManager();
		}
		return _instance;
	}

	private SysSettingCategoryManager() {
		this.setTableName("sys_setting_category");
		this.setTypeName("SysSettingCategory");
	}

	/*
	 * 取得实体
	 */
	public SysSettingCategory getEntity(String sysSettingCategoryCode) throws Exception {
		return (SysSettingCategory) super.getDataEntity(sysSettingCategoryCode);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysSettingCategory> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}
}
