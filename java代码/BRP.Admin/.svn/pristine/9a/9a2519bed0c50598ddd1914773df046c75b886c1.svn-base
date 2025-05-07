package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import strosoft.app.common.CodeDataManager;
import strosoft.sys.data.SysUiControl;
import strosoft.sys.data.SysUiControlMaster;


public class SysUiControlManager extends SysUiControlMaster {
	private static SysUiControlManager _instance;

	public static SysUiControlManager getInstance() {
		if (_instance == null) {
			_instance = new SysUiControlManager();
		}
		return _instance;
	}

	private SysUiControlManager() {
		this.setTableName("sys_ui_control");
		this.setTypeName("SysUiControl");
	}

	/*
	 * 取得实体
	 */
	public SysUiControl getEntity(String sysUiControlCode) throws Exception {
		return (SysUiControl) super.getDataEntity(sysUiControlCode);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysUiControl> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}
}
