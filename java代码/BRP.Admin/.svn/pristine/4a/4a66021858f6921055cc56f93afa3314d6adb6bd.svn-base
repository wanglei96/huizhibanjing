package BRP.model;

import BRP.data.CompanyRole;
import BRP.data.CompanyRoleMaster;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.data.DataEntity;

import java.io.IOException;
import java.util.List;

public class CompanyRoleManager extends CompanyRoleMaster {
    private static CompanyRoleManager _instance;

    private CompanyRoleManager() {
        this.setTableName("company_role");
        this.setTypeName("CompanyRole");
    }

    public static CompanyRoleManager getInstance() {
        if (_instance == null) {
            _instance = new CompanyRoleManager();
        }
        return _instance;
    }

    public CompanyRole getDataEntityByName(String name) throws Exception {
        String condition = "name = '" + name + "'";
        List<CompanyRole> companyRoleList = this.getList(condition);
        return companyRoleList.get(0);
    }

    public CompanyRole getDataEntityByName(SqlSession session, String name, Integer companyId) throws Exception {
        String condition = "name = '" + name + "' and company_id='" + companyId + "'";
        List<CompanyRole> companyRoleList = this.getList(session, condition);
        return companyRoleList.get(0);
    }

    /**
     * 根据商户用户ID取得列表
     * @param companyUserId
     * @return
     */

    public List<CompanyRole> getListByCompanyUserId(Integer companyUserId) throws Exception {
        String condition = String.format("id in (select company_role_id from company_user_role where company_user_id=%s)", companyUserId);
        return getList(condition);
    }
}