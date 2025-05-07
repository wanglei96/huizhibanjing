package BRP.service;

import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import BRP.model.CompanyOrganizationTreeNode;
import strosoft.app.service.ServiceHandler;
import strosoft.app.service.ServiceHandlerHelper;
import strosoft.app.util.JsonHelper;
import BRP.data.CompanyOrganization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/*
 * 获取组织机构树结构列表
 */
public class GetCompanyOrganizationTreeServiceHandler extends ServiceHandler {

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = getRequestData(request);
        Integer primaryKeyValue = JsonHelper.getInt(jData, "primaryKeyValue");

        String condition = ServiceHandlerHelper.getGetListCondition(request, this.getConfigNode());
        String sqlWhere = "";
        if (condition != null && !condition.equals("")) {
            sqlWhere = " where " + condition;
        }
        String sql = "SELECT * FROM `company_organization` "+sqlWhere+" order by display_order";
        ArrayList<LinkedHashMap<String, Object>> companyOrganizationHashMaps = MyBatisManager.getInstance().executeHashMapList(sql);
        List<CompanyOrganization> companyOrganizationList = JsonHelper.toJavaList(CompanyOrganization.class,companyOrganizationHashMaps);

        List<CompanyOrganizationTreeNode> companyOrganizationTree = new ArrayList<CompanyOrganizationTreeNode>();
        for (CompanyOrganization companyOrganization : companyOrganizationList) {
            if (Objects.equals(companyOrganization.getParentId() ,0)) {
                //创建最顶层节点
                CompanyOrganizationTreeNode companyOrganizationTreeNode = new CompanyOrganizationTreeNode();
                companyOrganizationTreeNode.setId(companyOrganization.getId());
                companyOrganizationTreeNode.setValue(companyOrganization.getId());
                companyOrganizationTreeNode.setParentId(companyOrganization.getParentId());
                companyOrganizationTreeNode.setLabel(companyOrganization.getName());
                companyOrganizationTreeNode.setDescription(companyOrganization.getDescription());
                if (companyOrganization.getId().equals(primaryKeyValue)){
                    companyOrganizationTreeNode.setDisabled(true);
                }else{
                    companyOrganizationTreeNode.setDisabled(false);
                }
                //获取所有子节点
                List<CompanyOrganizationTreeNode> companyOrganizationTreeChildren = getCompanyOrganizationTreeChildren(companyOrganizationList, companyOrganization,companyOrganizationTreeNode.getDisabled(),primaryKeyValue);
                if(!companyOrganizationTreeChildren.isEmpty()) {
                    companyOrganizationTreeNode.setChildren(companyOrganizationTreeChildren);
                }
                companyOrganizationTree.add(companyOrganizationTreeNode);
            }
        }
        this.writeSuccessResponse(response,companyOrganizationTree);
    }

    /**
     * 递归获取所有子节点
     * @param companyOrganizationList
     * @param companyOrganizationParent
     * @return
     * @throws Exception
     */
    public List<CompanyOrganizationTreeNode> getCompanyOrganizationTreeChildren(List<CompanyOrganization> companyOrganizationList, CompanyOrganization companyOrganizationParent,Boolean disabled,Integer primaryKeyValue) throws Exception {
        List<CompanyOrganizationTreeNode> companyOrganizationTree = new ArrayList<CompanyOrganizationTreeNode>();

        for (CompanyOrganization companyOrganization : companyOrganizationList) {
            if (Objects.equals(companyOrganization.getParentId(), companyOrganizationParent.getId())) {
                CompanyOrganizationTreeNode companyOrganizationTreeNode = new CompanyOrganizationTreeNode();
                companyOrganizationTreeNode.setId(companyOrganization.getId());
                companyOrganizationTreeNode.setValue(companyOrganization.getId());
                companyOrganizationTreeNode.setParentId(companyOrganization.getParentId());
                companyOrganizationTreeNode.setLabel(companyOrganization.getName());
                companyOrganizationTreeNode.setDescription(companyOrganization.getDescription());
                if (disabled || companyOrganization.getId().equals(primaryKeyValue)){
                    companyOrganizationTreeNode.setDisabled(true);
                }else{
                    companyOrganizationTreeNode.setDisabled(false);
                }
                //获取所有子节点
                List<CompanyOrganizationTreeNode> companyOrganizationTreeChildren = getCompanyOrganizationTreeChildren(companyOrganizationList, companyOrganization,companyOrganizationTreeNode.getDisabled(),primaryKeyValue);
                if(!companyOrganizationTreeChildren.isEmpty()){
                    companyOrganizationTreeNode.setChildren(companyOrganizationTreeChildren);
                }
                companyOrganizationTree.add(companyOrganizationTreeNode);
            }
        }
        return companyOrganizationTree;
    }
}