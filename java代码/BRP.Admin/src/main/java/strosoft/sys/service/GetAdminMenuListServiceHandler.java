package strosoft.sys.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import strosoft.app.common.EncryptManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.SysMenuManager;
import strosoft.sys.model.SysUserManager;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 取得用户菜单列表
 */
public class GetAdminMenuListServiceHandler extends ServiceHandler {

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException,
			NoSuchAlgorithmException {

		String data = request.getParameter("data");
		JSONObject jData = JSONObject.fromObject(data);
		int sysUserId = JsonHelper.getInt(jData, "sysUserId", "SysUserId",
				"sys_user_id");
//		if (sysUserId == 0) {
//			this.writeErrorResponse(response, "用户ID不能为空！");
//			return;
//		}
		//获取所有菜单列表
		ArrayList<SysMenu> allSysMenu = SysMenuManager.getInstance()
				.getList();
		//把所有菜单列表转换为Key为id的 hashtable
		Hashtable<Integer,SysMenu> sysMenuHashtable =  SysMenuManager.getInstance().getSysMenuHashtable(allSysMenu);
		//获取查询的菜单列表
		ArrayList<SysMenu> lstSysMenu = SysMenuManager.getInstance()
				.getAdminListBySysUserId(sysUserId);
		//添加父菜单
		for(int i = 0; i <= lstSysMenu.size() - 1; i++){
			SysMenu curentSysMenu = lstSysMenu.get(i);
			List<SysMenu> lstParentsMenu = SysMenuManager.getInstance()
					.getParentsList(curentSysMenu.getId(),sysMenuHashtable);
			for (SysMenu currentParentMenu : lstParentsMenu) {
				Boolean exists = existsSysMenu(lstSysMenu,
						currentParentMenu.getId());
				if(!exists){
					lstSysMenu.add(currentParentMenu);
				}
			}
		}

		//按显示顺序排序
		for(int i = 0; i < lstSysMenu.size() - 1; i++){
			for(int j = i + 1; j < lstSysMenu.size();j++){
				if(lstSysMenu.get(i).getDisplayOrder() > lstSysMenu.get(j).getDisplayOrder() ){
					SysMenu tmpSysMenu = lstSysMenu.get(i);
					lstSysMenu.set(i, lstSysMenu.get(j));
					lstSysMenu.set(j, tmpSysMenu);
				}
			}
		}
		this.writeSuccessResponse(response, lstSysMenu);

	}

	private Boolean existsSysMenu(ArrayList<SysMenu> lstSysMenu, int id) {
		for (SysMenu currentSysMenu : lstSysMenu) {
			if (currentSysMenu.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
