package BRP.service;

import BRP.data.Company;
import BRP.data.CompanyUser;
import BRP.model.CompanyUserManager;
import strosoft.app.common.EncryptManager;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.UpdateServiceHandler;

public class UpdateCompanyServiceHandler extends UpdateServiceHandler {
    @Override
    protected void postProcess(ServiceContext updateServiceContext) throws Exception {
        int newId = updateServiceContext.getNewId();
        Company theCompany = (Company) updateServiceContext.getDataEntity();
        //获取门店超级管理员用户
        CompanyUser theCompanyUser = CompanyUserManager.getInstance().getCompanyAdminUser(updateServiceContext.session, theCompany.getId());
        if(theCompanyUser!=null){
            theCompanyUser.setLoginName(theCompany.getLoginName());
            theCompanyUser.setLicenseImageFileId(theCompany.getLicenseImageFileId());
            theCompanyUser.setMobilePhone(theCompany.getMobilePhone());
            theCompanyUser.setPassword(theCompany.getPassword());
            CompanyUserManager.getInstance().update(updateServiceContext.session, theCompanyUser);
        }
    }
}
