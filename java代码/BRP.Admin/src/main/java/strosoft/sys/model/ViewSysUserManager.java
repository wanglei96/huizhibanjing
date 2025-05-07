package strosoft.sys.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.IdDataManager;
import strosoft.app.common.MyBatisManager;
import strosoft.sys.data.ViewSysUser;

/*
 * 用户管理类
 */
public class ViewSysUserManager extends IdDataManager {
	private static ViewSysUserManager _instance;

	public static ViewSysUserManager getInstance() {
		if (_instance == null) {
			_instance = new ViewSysUserManager();
		}
		return _instance;
	}

	private ViewSysUserManager() {
		this.setTableName("view_sys_user");
		this.setTypeName("ViewSysUser");
	}

	/*
	 * 取得实体
	 */
	public ViewSysUser getEntity(int viewSysUserID) throws Exception {
		return (ViewSysUser) super.getDataEntity(viewSysUserID);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ViewSysUser> getList() throws IOException {
		List<Object> lstDataEntity = super.getObjectList();
		return super.toList(lstDataEntity);
	}

	/**
	 * 根据登录账号取得实体
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public ViewSysUser getEntityByLoginName(String loginName) throws Exception {
		String condition = String.format("login_name='%s'", loginName);
		List<ViewSysUser> lstViewSysUser = this.getList(condition);
		if(lstViewSysUser.size() == 0){
			return null;
		}
		return lstViewSysUser.get(0);
	}
}
