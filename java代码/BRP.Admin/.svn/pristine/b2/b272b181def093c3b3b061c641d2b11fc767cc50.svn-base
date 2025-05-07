package BRP.service;

import BRP.data.*;
import BRP.model.*;
import strosoft.app.common.EncryptManager;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.sys.model.PermissionManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class AddCompanyServiceHandler extends AddServiceHandler {
    /**
     * 增加后处理函数
     *
     * @param newAddServiceContext
     * @throws Exception
     */
    @Override
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        //添加门店超级管理员
        Company company = CompanyManager.getInstance().getEntityById(newAddServiceContext.session, newAddServiceContext.getNewId());

        CompanyUser newCompanyUser = new CompanyUser();
        newCompanyUser.setCompanyId(newAddServiceContext.newId);
        newCompanyUser.setLoginName(company.getLoginName());
        newCompanyUser.setPassword(company.getPassword());
        newCompanyUser.setMobilePhone(company.getMobilePhone());
        newCompanyUser.setEnabled(true);
        newCompanyUser.setName("超级管理员");
        newCompanyUser.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        //String randomNo = MyBatisManager.getInstance().createRandomNo(newAddServiceContext.getSession(), "company_user", "employee_no", 10);
        newCompanyUser.setEmployeeNo(company.getCompanyNo()+"001");
        int newId = CompanyUserManager.getInstance().add(newAddServiceContext.session, newCompanyUser);

        //添加“超级管理员”角色和“配送员”角色
        CompanyRole newAdminCompanyRole = new CompanyRole();
        newAdminCompanyRole.setName("超级管理员");
        newAdminCompanyRole.setCompanyId(newAddServiceContext.newId);
        newAdminCompanyRole.setCreateTime(new Timestamp(System.currentTimeMillis()));

        CompanyRole newdelivererCompanyRole = new CompanyRole();
        newdelivererCompanyRole.setName("配送员");
        newdelivererCompanyRole.setCompanyId(newAddServiceContext.newId);
        newdelivererCompanyRole.setCreateTime(new Timestamp(System.currentTimeMillis()));

        CompanyRole newBookpickerCompanyRole = new CompanyRole();
        newBookpickerCompanyRole.setName("拣书员");
        newBookpickerCompanyRole.setCompanyId(newAddServiceContext.newId);
        newBookpickerCompanyRole.setCreateTime(new Timestamp(System.currentTimeMillis()));

        List<CompanyRole> newCompanyRoleList = new ArrayList<>();
        newCompanyRoleList.add(newAdminCompanyRole);
        newCompanyRoleList.add(newdelivererCompanyRole);
        newCompanyRoleList.add(newBookpickerCompanyRole);
        //添加角色
        CompanyRoleManager.getInstance().addList(newAddServiceContext.session, newCompanyRoleList);

        //设置超级管理员角色
        CompanyRole companyRole = CompanyRoleManager.getInstance().getDataEntityByName(newAddServiceContext.session, "超级管理员", company.getId());
        CompanyUserRole companyUserRole = new CompanyUserRole();
        companyUserRole.setCompanyRoleId(companyRole.getId());
        companyUserRole.setCompanyUserId(newId);
        CompanyUserRoleManager.getInstance().add(newAddServiceContext.session, companyUserRole);

        //重置所有门店超级管理员权限
        PermissionManager.getInstance().resetAllCompanyAdminPermissions(newAddServiceContext.session);
        //补充所有门店系统参数
        CompanySettingManager.getInstance().completionCompanySetting(newAddServiceContext.session);
    }
}