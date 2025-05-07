package BRP.model;

import BRP.data.ViewCompanyUser;
import BRP.data.ViewCompanyUserMaster;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;

import java.util.List;

public class ViewCompanyUserManager extends ViewCompanyUserMaster {
    private static ViewCompanyUserManager _instance;

    public static ViewCompanyUserManager getInstance() {
        if (_instance == null) {
            _instance = new ViewCompanyUserManager();
        }
        return _instance;
    }

    private ViewCompanyUserManager() {
        this.setTypeName("ViewCompanyUser");
    }

    public ViewCompanyUser getEntityByLoginName(String loginName) throws Exception {
        String condition = "login_name='" + loginName + "'";
        List<ViewCompanyUser> lstViewCompanyUser = this.getList(condition);
        if (lstViewCompanyUser != null && lstViewCompanyUser.size() > 0) {
            return lstViewCompanyUser.get(0);
        }
        return null;
    }

    /**
     * 根据用户名和手机号查询商户
     *
     * @param loginName
     * @param mobilePhone
     * @return
     * @throws Exception
     */
    public ViewCompanyUser getEntityByLoginNameAndMobilePhone(String loginName, String mobilePhone) throws Exception {
        String condition = "login_name='" + loginName + "' and mobile_phone=" + mobilePhone;
        List<ViewCompanyUser> lstViewCompanyUser = this.getList(condition);
        if (lstViewCompanyUser != null && lstViewCompanyUser.size() > 0) {
            return lstViewCompanyUser.get(0);
        }
        return null;
    }

    /**
     * 根据手机号查询商户
     *
     * @param mobilePhone
     * @return
     * @throws Exception
     */
    public ViewCompanyUser getEntityByMobilePhone(String mobilePhone) throws Exception {
        String condition = "mobile_phone=" + mobilePhone;
        List<ViewCompanyUser> lstViewCompanyUser = this.getList(condition);
        if (lstViewCompanyUser != null && lstViewCompanyUser.size() > 0) {
            return lstViewCompanyUser.get(0);
        }
        return null;
    }
}