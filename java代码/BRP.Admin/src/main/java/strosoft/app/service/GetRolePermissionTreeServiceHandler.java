package strosoft.app.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.model.RolePermissionTreeNode;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysMenu;
import strosoft.sys.data.SysRolePermission;
import strosoft.sys.model.SysRolePermissionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/*
 * 获取角色权限树
 */
public class GetRolePermissionTreeServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JSONObject jData = getRequestData(request);
        String sysRoleId = JsonHelper.getString(jData, "sysRoleId");

        String sql = "SELECT * FROM `sys_menu` where sys_menu_type_code='Platform' and value!='Platform' order by display_order";
        ArrayList<LinkedHashMap<String, Object>> sysMenuHashMaps = MyBatisManager.getInstance().executeHashMapList(sql);
        List<SysMenu> sysMenuList = JsonHelper.toJavaList(SysMenu.class,sysMenuHashMaps);

        List<RolePermissionTreeNode> rolePermissionTree = new ArrayList<RolePermissionTreeNode>();
        for (SysMenu sysMenu : sysMenuList) {
            if (Objects.equals(sysMenu.getParentId() ,112)) {
                //创建最顶层节点
                RolePermissionTreeNode rolePermissionTreeNode = new RolePermissionTreeNode();
                rolePermissionTreeNode.setPermission(sysMenu.getValue());
                rolePermissionTreeNode.setValue(sysMenu.getValue());
                rolePermissionTreeNode.setParentId(sysMenu.getParentId());
                rolePermissionTreeNode.setLabel(sysMenu.getName());
                //获取所有子节点
                List<RolePermissionTreeNode> rolePermissionTreeChildren = getRolePermissionTreeChildren(sysMenuList, sysMenu);
                if(!rolePermissionTreeChildren.isEmpty()) {
                    rolePermissionTreeNode.setChildren(rolePermissionTreeChildren);
                }
                rolePermissionTree.add(rolePermissionTreeNode);
            }
        }

        String getPermissionListSql = "SELECT * from sys_role_permission where sys_role_id="+sysRoleId+" and 0<(SELECT COUNT(*) from `sys_menu` where `value`=sys_role_permission.permission and url is not null and url<>'')";
        ArrayList<LinkedHashMap<String, Object>> sysRolePermissionList = MyBatisManager.getInstance().executeHashMapList(getPermissionListSql);
        ArrayList<String> rolePermissionList = new ArrayList<String>();
        for (LinkedHashMap<String, Object> sysRolePermission : sysRolePermissionList) {
            rolePermissionList.add((String)sysRolePermission.get("permission"));
        }
        com.alibaba.fastjson2.JSONObject resultJsonObject = new com.alibaba.fastjson2.JSONObject();
        resultJsonObject.put("rolePermissionTree",rolePermissionTree);
        resultJsonObject.put("rolePermissionList",rolePermissionList);
        this.writeSuccessResponse(response,resultJsonObject);
    }

    /**
     * 递归获取所有子节点
     * @param sysMenuList
     * @param sysMenuParent
     * @return
     * @throws Exception
     */
    public List<RolePermissionTreeNode> getRolePermissionTreeChildren(List<SysMenu> sysMenuList, SysMenu sysMenuParent) throws Exception {
        //所有子节点
        List<RolePermissionTreeNode> rolePermissionTree = new ArrayList<RolePermissionTreeNode>();

        for (SysMenu sysMenu : sysMenuList) {
            if (Objects.equals(sysMenu.getParentId(), sysMenuParent.getId())) {
                RolePermissionTreeNode rolePermissionTreeNode = new RolePermissionTreeNode();
                rolePermissionTreeNode.setPermission(sysMenu.getValue());
                rolePermissionTreeNode.setValue(sysMenu.getValue());
                rolePermissionTreeNode.setParentId(sysMenu.getParentId());
                rolePermissionTreeNode.setLabel(sysMenu.getName());
                //获取所有子节点
                List<RolePermissionTreeNode> rolePermissionTreeChildren = getRolePermissionTreeChildren(sysMenuList, sysMenu);
                if(!rolePermissionTreeChildren.isEmpty()){
                    rolePermissionTreeNode.setChildren(rolePermissionTreeChildren);
                }
                rolePermissionTree.add(rolePermissionTreeNode);
            }
        }
        return rolePermissionTree;
    }
}