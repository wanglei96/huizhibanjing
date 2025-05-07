package strosoft.sys.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.SysMenuManager;
import net.sf.json.JSONObject;

/*
 * 取得用户菜单列表
 */
public class GetSysMenuListBySysMenuTypeCodeServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JSONObject jData = this.getRequestData(request);
		String sysMenuTypeCode = jData.getString("sysMenuTypeCode");
		// 获取所有菜单列表
		ArrayList<SysMenu> allSysMenu = SysMenuManager.getInstance().getList();
		// 把所有菜单列表转换为Key为id的 hashtable
		Hashtable<Integer, SysMenu> sysMenuHashtable = SysMenuManager.getInstance().getSysMenuHashtable(allSysMenu);
		// 获取查询的菜单列表
		List<SysMenu> lstSysMenu = SysMenuManager.getInstance().getListBySysMenuTypeCode(sysMenuTypeCode);
		// 添加父菜单
		for (int i = 0; i <= lstSysMenu.size() - 1; i++) {
			SysMenu curentSysMenu = lstSysMenu.get(i);
			List<SysMenu> lstParentsMenu = SysMenuManager.getInstance().getParentsList(curentSysMenu.getId(),
					sysMenuHashtable);
			for (SysMenu currentParentMenu : lstParentsMenu) {
				Boolean exists = existsSysMenu(lstSysMenu, currentParentMenu.getId());
				if (!exists) {
					lstSysMenu.add(currentParentMenu);
				}
			}
		}

		// 按显示顺序排序
		for (int i = 0; i < lstSysMenu.size() - 1; i++) {
			for (int j = i + 1; j < lstSysMenu.size(); j++) {
				if (lstSysMenu.get(i).getDisplayOrder() > lstSysMenu.get(j).getDisplayOrder()) {
					SysMenu tmpSysMenu = lstSysMenu.get(i);
					lstSysMenu.set(i, lstSysMenu.get(j));
					lstSysMenu.set(j, tmpSysMenu);
				}
			}
		}
		this.writeSuccessResponse(response, lstSysMenu);

	}

	private Boolean existsSysMenu(List<SysMenu> lstSysMenu, int id) {
		for (SysMenu currentSysMenu : lstSysMenu) {
			if (currentSysMenu.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
