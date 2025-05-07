package strosoft.app.service;

import com.alibaba.fastjson2.JSONArray;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.model.SysOrganizationTreeNode;
import strosoft.app.util.ClassHelper;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysOrganization;
import strosoft.sys.model.SysOrganizationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/*
 * 获取组织机构树结构列表
 */
public class GetSysOrganizationTreeServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = getRequestData(request);
        Integer primaryKeyValue = JsonHelper.getInt(jData, "primaryKeyValue");

        String condition = ServiceHandlerHelper.getGetListCondition(request, this.getConfigNode());
        String sqlWhere = "";
        if (condition != null && !condition.equals("")) {
            sqlWhere = " where " + condition;
        }
        String sql = "SELECT * FROM `sys_organization` "+sqlWhere+" order by display_order";
        ArrayList<LinkedHashMap<String, Object>> sysOrganizationHashMaps = MyBatisManager.getInstance().executeHashMapList(sql);
        List<SysOrganization> sysOrganizationList = JsonHelper.toJavaList(SysOrganization.class,sysOrganizationHashMaps);

        List<SysOrganizationTreeNode> sysOrganizationTree = new ArrayList<SysOrganizationTreeNode>();
        for (SysOrganization sysOrganization : sysOrganizationList) {
            if (Objects.equals(sysOrganization.getParentId() ,0)) {
                //创建最顶层节点
                SysOrganizationTreeNode sysOrganizationTreeNode = new SysOrganizationTreeNode();
                sysOrganizationTreeNode.setId(sysOrganization.getId());
                sysOrganizationTreeNode.setValue(sysOrganization.getId());
                sysOrganizationTreeNode.setParentId(sysOrganization.getParentId());
                sysOrganizationTreeNode.setLabel(sysOrganization.getName());
                sysOrganizationTreeNode.setDescription(sysOrganization.getDescription());
                if (sysOrganization.getId().equals(primaryKeyValue)){
                    sysOrganizationTreeNode.setDisabled(true);
                }else{
                    sysOrganizationTreeNode.setDisabled(false);
                }
                //获取所有子节点
                List<SysOrganizationTreeNode> sysOrganizationTreeChildren = getSysOrganizationTreeChildren(sysOrganizationList, sysOrganization,sysOrganizationTreeNode.getDisabled(),primaryKeyValue);
                if(!sysOrganizationTreeChildren.isEmpty()) {
                    sysOrganizationTreeNode.setChildren(sysOrganizationTreeChildren);
                }
                sysOrganizationTree.add(sysOrganizationTreeNode);
            }
        }
        this.writeSuccessResponse(response,sysOrganizationTree);
    }

    /**
     * 递归获取所有子节点
     * @param sysOrganizationList
     * @param sysOrganizationParent
     * @return
     * @throws Exception
     */
    public List<SysOrganizationTreeNode> getSysOrganizationTreeChildren(List<SysOrganization> sysOrganizationList, SysOrganization sysOrganizationParent,Boolean disabled,Integer primaryKeyValue) throws Exception {
        List<SysOrganizationTreeNode> sysOrganizationTree = new ArrayList<SysOrganizationTreeNode>();

        for (SysOrganization sysOrganization : sysOrganizationList) {
            if (Objects.equals(sysOrganization.getParentId(), sysOrganizationParent.getId())) {
                SysOrganizationTreeNode sysOrganizationTreeNode = new SysOrganizationTreeNode();
                sysOrganizationTreeNode.setId(sysOrganization.getId());
                sysOrganizationTreeNode.setValue(sysOrganization.getId());
                sysOrganizationTreeNode.setParentId(sysOrganization.getParentId());
                sysOrganizationTreeNode.setLabel(sysOrganization.getName());
                sysOrganizationTreeNode.setDescription(sysOrganization.getDescription());
                if (disabled || sysOrganization.getId().equals(primaryKeyValue)){
                    sysOrganizationTreeNode.setDisabled(true);
                }else{
                    sysOrganizationTreeNode.setDisabled(false);
                }
                //获取所有子节点
                List<SysOrganizationTreeNode> sysOrganizationTreeChildren = getSysOrganizationTreeChildren(sysOrganizationList, sysOrganization,sysOrganizationTreeNode.getDisabled(),primaryKeyValue);
                if(!sysOrganizationTreeChildren.isEmpty()){
                    sysOrganizationTreeNode.setChildren(sysOrganizationTreeChildren);
                }
                sysOrganizationTree.add(sysOrganizationTreeNode);
            }
        }
        return sysOrganizationTree;
    }
}