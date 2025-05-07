package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.model.RolePermissionTreeNode;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysMenu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/*
 * 获取角色权限树
 */
public class GetCompanyRolePermissionTreeServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JSONObject jData = getRequestData(request);
        String companyRoleId = JsonHelper.getString(jData, "companyRoleId");

        String sql = "SELECT * FROM `sys_menu` where sys_menu_type_code='Company' and value!='Company' order by display_order";
        ArrayList<LinkedHashMap<String, Object>> companyMenuHashMaps = MyBatisManager.getInstance().executeHashMapList(sql);
        List<SysMenu> companyMenuList = JsonHelper.toJavaList(SysMenu.class,companyMenuHashMaps);

        List<RolePermissionTreeNode> rolePermissionTree = new ArrayList<RolePermissionTreeNode>();
        for (SysMenu companyMenu : companyMenuList) {
            if (Objects.equals(companyMenu.getParentId() ,133)) {
                //创建最顶层节点
                RolePermissionTreeNode rolePermissionTreeNode = new RolePermissionTreeNode();
                rolePermissionTreeNode.setPermission(companyMenu.getValue());
                rolePermissionTreeNode.setValue(companyMenu.getValue());
                rolePermissionTreeNode.setParentId(companyMenu.getParentId());
                rolePermissionTreeNode.setLabel(companyMenu.getName());
                //获取所有子节点
                List<RolePermissionTreeNode> rolePermissionTreeChildren = getRolePermissionTreeChildren(companyMenuList, companyMenu);
                if(!rolePermissionTreeChildren.isEmpty()) {
                    rolePermissionTreeNode.setChildren(rolePermissionTreeChildren);
                }
                rolePermissionTree.add(rolePermissionTreeNode);
            }
        }

        String getPermissionListSql = "SELECT * from company_role_permission where company_role_id="+companyRoleId+" and 0<(SELECT COUNT(*) from `sys_menu` where `value`=company_role_permission.permission and url is not null and url<>'')";
        ArrayList<LinkedHashMap<String, Object>> companyRolePermissionList = MyBatisManager.getInstance().executeHashMapList(getPermissionListSql);
        ArrayList<String> rolePermissionList = new ArrayList<String>();
        for (LinkedHashMap<String, Object> companyRolePermission : companyRolePermissionList) {
            rolePermissionList.add((String)companyRolePermission.get("permission"));
        }
        com.alibaba.fastjson2.JSONObject resultJsonObject = new com.alibaba.fastjson2.JSONObject();
        resultJsonObject.put("rolePermissionTree",rolePermissionTree);
        resultJsonObject.put("rolePermissionList",rolePermissionList);
        this.writeSuccessResponse(response,resultJsonObject);
    }

    /**
     * 递归获取所有子节点
     * @param companyMenuList
     * @param companyMenuParent
     * @return
     * @throws Exception
     */
    public List<RolePermissionTreeNode> getRolePermissionTreeChildren(List<SysMenu> companyMenuList, SysMenu companyMenuParent) throws Exception {
        //所有子节点
        List<RolePermissionTreeNode> rolePermissionTree = new ArrayList<RolePermissionTreeNode>();

        for (SysMenu companyMenu : companyMenuList) {
            if (Objects.equals(companyMenu.getParentId(), companyMenuParent.getId())) {
                RolePermissionTreeNode rolePermissionTreeNode = new RolePermissionTreeNode();
                rolePermissionTreeNode.setPermission(companyMenu.getValue());
                rolePermissionTreeNode.setValue(companyMenu.getValue());
                rolePermissionTreeNode.setParentId(companyMenu.getParentId());
                rolePermissionTreeNode.setLabel(companyMenu.getName());
                //获取所有子节点
                List<RolePermissionTreeNode> rolePermissionTreeChildren = getRolePermissionTreeChildren(companyMenuList, companyMenu);
                if(!rolePermissionTreeChildren.isEmpty()){
                    rolePermissionTreeNode.setChildren(rolePermissionTreeChildren);
                }
                rolePermissionTree.add(rolePermissionTreeNode);
            }
        }
        return rolePermissionTree;
    }
}