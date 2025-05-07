package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.model.SysMenuManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/*
 * 取得用户菜单列表
 */
public class GetPlatformMenuListServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String data = request.getParameter("data");
        JSONObject jData = JSONObject.fromObject(data);
        Integer sysUserId = JsonHelper.getInt(jData, "sysUserId");

        // 获取查询的菜单列表
        ArrayList<SysMenu> lstSysMenu = SysMenuManager.getInstance()
                .getListBySysUserIdAndMenuTypeCode(sysUserId, "Platform");
        JSONArray menuArray = new JSONArray();

        // 获取顶层菜单
        ArrayList<SysMenu> topLevelMenus = getTopMenusByParentId(lstSysMenu);
        for (SysMenu topLevelMenu : topLevelMenus) {
            JSONObject menuObject = convertMenuToJson(topLevelMenu, lstSysMenu);
            menuArray.add(menuObject);
        }
        this.writeSuccessResponse(response, menuArray);
    }

    // 根据 parent_id 为Comapny获取菜单列表
    private ArrayList<SysMenu> getTopMenusByParentId(ArrayList<SysMenu> allSysMenu) throws Exception {
        ArrayList<SysMenu> menus = new ArrayList<>();
        /*SysMenu result = allSysMenu.stream()
                .filter(sysMenu -> sysMenu.getValue().equals("Company"))
                .findFirst() // 找到第一条满足条件的数据
                .orElse(null);*/
        List<SysMenu> lstSysMenu = SysMenuManager.getInstance().getList("value='Platform'");
        for (SysMenu menu : allSysMenu) {
            if (menu.getParentId().equals(lstSysMenu.get(0).getId())) {
                menus.add(menu);
            }
        }
        return menus;
    }

    // 将菜单对象转换为 JSON 格式
    private JSONObject convertMenuToJson(SysMenu menu, ArrayList<SysMenu> allSysMenu) {
        JSONObject menuObject = new JSONObject();
        menuObject.put("menuId", menu.getId());
        menuObject.put("name", menu.getName());
        String path = this.getMenuPath(menu);
        menuObject.put("path", path);
        JSONObject jQuery = this.getMenuQuery(menu);
        if (jQuery != null) {
            menuObject.put("query", jQuery);
        }
        menuObject.put("menuType", 0);
        if (StringHelper.isNullOrEmpty(menu.getIcon())) {
            menuObject.put("icon", "House");
        } else {
            menuObject.put("icon", menu.getIcon());
        }
        menuObject.put("sort", 1);
        menuObject.put("component", menu.getUrl());
        JSONObject metaObject = new JSONObject();
        metaObject.put("title", menu.getName());
        menuObject.put("meta", metaObject);

        // 获取子菜单
        ArrayList<SysMenu> children = getMenusByParentId(allSysMenu, menu.getId());
        if (!children.isEmpty()) {
            JSONArray childrenArray = new JSONArray();
            for (SysMenu child : children) {
                JSONObject childObject = convertMenuToJson(child, allSysMenu);
                childrenArray.add(childObject);
            }
            menuObject.put("children", childrenArray);
        }

        return menuObject;
    }

    /**
     * 取得查询对象
     *
     * @param menu
     * @return
     */
    private JSONObject getMenuQuery(SysMenu menu) {
        if (StringHelper.isNullOrEmpty(menu.getPath())) {
            return null;
        }
        String path = menu.getPath();
        if (path.indexOf("?") > 0) {
            JSONObject jQuery = new JSONObject();
            String strQuery = path.substring(path.indexOf("?") + 1);
            String[] arrQuery = strQuery.split("&");
            for (String strPart : arrQuery) {
                String[] arrPart = strPart.split("=");
                jQuery.put(arrPart[0], arrPart[1]);
            }
            return jQuery;
        }
        return null;
    }

    private String getMenuPath(SysMenu menu) {
        if (StringHelper.isNullOrEmpty(menu.getPath())) {
            if (StringHelper.isNullOrEmpty(menu.getUrl())) {
                return menu.getValue();
            } else {
                int lastIndex = menu.getUrl().lastIndexOf(".");
                String path = menu.getUrl().substring(0, lastIndex);
                return path;
            }
        } else {
            String path = menu.getPath();
            if (path.indexOf("?") > 0) {
                path = path.substring(0, path.indexOf("?"));
            }
            return path;
        }
    }

    // 根据 parent_id 获取菜单列表
    private static ArrayList<SysMenu> getMenusByParentId(ArrayList<SysMenu> allSysMenu, Integer parentId) {
        ArrayList<SysMenu> menus = new ArrayList<>();
        for (SysMenu menu : allSysMenu) {
            if (menu.getParentId().equals(parentId)) {
                menus.add(menu);
            }
        }
        return menus;
    }
}
