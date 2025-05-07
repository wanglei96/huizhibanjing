package BRP.model;

import java.util.List;

/**
 * 角色权限树节点
 */
public class CompanyRolePermissionTreeNode {
    private String permission;
    private String value;
    private Integer parentId;
    private String label;
    private List<CompanyRolePermissionTreeNode> children;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CompanyRolePermissionTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<CompanyRolePermissionTreeNode> children) {
        this.children = children;
    }
}
