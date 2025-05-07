package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import strosoft.app.common.CodeDataManager;
import strosoft.sys.data.SysDataType;
import strosoft.sys.data.SysDataTypeMaster;


public class SysDataTypeManager extends SysDataTypeMaster {
	private static SysDataTypeManager _instance;

	public static SysDataTypeManager getInstance() {
		if (_instance == null) {
			_instance = new SysDataTypeManager();
		}
		return _instance;
	}

	private SysDataTypeManager() {
		this.setTableName("sys_data_type");
		this.setTypeName("SysDataType");
	}

	/*
	 * 取得实体
	 */
	public SysDataType getEntity(String sysDataTypeCode) throws Exception {
		return (SysDataType) super.getDataEntity(sysDataTypeCode);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SysDataType> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}
}
