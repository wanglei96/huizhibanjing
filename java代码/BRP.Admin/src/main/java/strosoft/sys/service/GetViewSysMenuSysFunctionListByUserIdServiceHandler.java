package strosoft.sys.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.EncryptManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.data.SysRolePermission;
import strosoft.sys.data.SysUserRole;
import strosoft.sys.data.ViewSysMenuSysFunction;
import strosoft.sys.model.SysMenuManager;
import strosoft.sys.model.SysRoleCmsChannelPermissionManager;
import strosoft.sys.model.SysRolePermissionManager;
import strosoft.sys.model.SysUserManager;
import strosoft.sys.model.ViewSysMenuSysFunctionManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 取得用户菜单列表
 */
public class GetViewSysMenuSysFunctionListByUserIdServiceHandler extends
		ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			NoSuchAlgorithmException {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int sysUserId = JsonHelper.getInt(jData, "sysUserId", "SysUserId",
				"sys_user_id");
		String typeCode = jData.getString("typeCode");
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisManager.getInstance().openSession();
			// 获取所有菜单、功能列表
			List<ViewSysMenuSysFunction> allSysMenuSysFuntion = ViewSysMenuSysFunctionManager
					.getInstance().getList(sqlSession,
							" sys_menu_type_code='" + typeCode + "'");
			// 把所有菜单、功能列表转换为Key为id的 hashtable
			Hashtable<String, ViewSysMenuSysFunction> sysMenuSysFunctionIdHashtable = ViewSysMenuSysFunctionManager
					.getInstance().getViewSysMenuSysFunctionIdHashtable(
							allSysMenuSysFuntion);
			// 把所有菜单、功能列表转换为Key为value的 hashtable
			Hashtable<String, ViewSysMenuSysFunction> sysMenuSysFunctionValueHashtable = ViewSysMenuSysFunctionManager
					.getInstance().getViewSysMenuSysFunctionValueHashtable(
							allSysMenuSysFuntion);
			// 获取查询的菜单、功能列表
			List<SysRolePermission> sysRolePermissions = SysRolePermissionManager
					.getInstance().getListBySysUserId(sqlSession, sysUserId);
			// 添加父菜单
			ArrayList<ViewSysMenuSysFunction> result = new ArrayList<ViewSysMenuSysFunction>();
			for (int i = 0; i <= sysRolePermissions.size() - 1; i++) {
				SysRolePermission sysRolePermission = sysRolePermissions.get(i);
				ViewSysMenuSysFunction currentSysMenuSysFunction = sysMenuSysFunctionValueHashtable
						.get(sysRolePermission.getPermission());
				getParentList(sysMenuSysFunctionIdHashtable, sysMenuSysFunctionValueHashtable, currentSysMenuSysFunction, result);
			}

			// 按显示顺序排序
			for (int i = 0; i < result.size() - 1; i++) {
				for (int j = i + 1; j < result.size(); j++) {
					if (result.get(i).getDisplayOrder().compareTo(result.get(j)
							.getDisplayOrder()) > 0) {
						ViewSysMenuSysFunction tmpSysMenu = result.get(i);
						result.set(i, result.get(j));
						result.set(j, tmpSysMenu);
					}
				}
			}
			this.writeSuccessResponse(response, result);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			this.writeErrorResponse(response, "获取菜单失败");
		} finally {
			sqlSession.close();
		}
	}

	private void getParentList(
			Hashtable<String, ViewSysMenuSysFunction> sysMenuSysFunctionIdHashtable,
			Hashtable<String, ViewSysMenuSysFunction> sysMenuSysFunctionValueHashtable,
			ViewSysMenuSysFunction currentSysMenuSysFunction,
			ArrayList<ViewSysMenuSysFunction> result) {
		if(null == currentSysMenuSysFunction){
			return;
		}
		result = addToResult(result, currentSysMenuSysFunction);
		// 获取父菜单
		Integer parentId = currentSysMenuSysFunction.getParentId();
		if (parentId != 0 && null != parentId) {
			ViewSysMenuSysFunction parentSysMenuSysFunction = sysMenuSysFunctionIdHashtable
					.get(parentId.toString());
			getParentList(sysMenuSysFunctionIdHashtable, sysMenuSysFunctionValueHashtable, parentSysMenuSysFunction, result);
		}

	}

	private ArrayList<ViewSysMenuSysFunction> addToResult(
			ArrayList<ViewSysMenuSysFunction> result,
			ViewSysMenuSysFunction item) {
		Boolean exists = existsSysMenuSysFuntion(result, item.getId());
		if (!exists) {
			result.add(item);
		}
		return result;
	}

	private Boolean existsSysMenuSysFuntion(
			ArrayList<ViewSysMenuSysFunction> lstSysMenuSysFunction, String id) {
		for (ViewSysMenuSysFunction currentSysMenuSysFunction : lstSysMenuSysFunction) {
			if (currentSysMenuSysFunction.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
