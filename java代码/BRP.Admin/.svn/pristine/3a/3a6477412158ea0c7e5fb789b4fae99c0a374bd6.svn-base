package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import strosoft.app.common.CodeDataManager;
import strosoft.sys.data.ViewSysSetting;

public class ViewSysSettingManager extends CodeDataManager{
	private static ViewSysSettingManager _instance;

	public static ViewSysSettingManager getInstance() {
		if (_instance == null) {
			_instance = new ViewSysSettingManager();
		}
		return _instance;
	}

	private ViewSysSettingManager() {
		this.setTableName("view_sys_setting");
		this.setTypeName("ViewSysSetting");
	}

	/*
	 * 取得实体
	 */
	public ViewSysSetting getEntity(String sysSettingCode) throws Exception {
		return (ViewSysSetting) super.getDataEntity(sysSettingCode);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ViewSysSetting> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}
}
