package BRP.service;

import BRP.data.Company;
import BRP.data.CompanyRole;
import BRP.data.CompanyUser;
import BRP.data.CompanyUserRole;
import BRP.model.*;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 取得平台端统计数据
 */
public class GetPlatformTotalServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
        PlatformTotalInfo newPlatformTotalInfo = new PlatformTotalInfo();
        int companyCount = CompanyManager.getInstance().getCount("1=1");
        int memberCount = MemberManager.getInstance().getCount("1=1");
        newPlatformTotalInfo.setCompanyCount(companyCount);
        newPlatformTotalInfo.setMemberCount(memberCount);
        this.writeSuccessResponse(response, newPlatformTotalInfo);
    }
}