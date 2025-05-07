package BRP.service;

import BRP.data.Company;
import BRP.data.CompanyRole;
import BRP.data.CompanyUser;
import BRP.data.CompanyUserRole;
import BRP.model.CompanyManager;
import BRP.model.CompanyRoleManager;
import BRP.model.CompanyUserManager;
import BRP.model.CompanyUserRoleManager;
import net.sf.json.JSONObject;
import org.springframework.util.CollectionUtils;
import strosoft.app.common.EncryptManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 配送员编辑
 */
public class EditDelivererServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer id = JsonHelper.getInt(jData, "id");
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        String mobilePhone = JsonHelper.getString(jData, "mobilePhone");
        String password = JsonHelper.getString(jData, "password");
        String name = JsonHelper.getString(jData, "name");
        Integer deliverAreaId = JsonHelper.getInt(jData, "deliverAreaId");
        List<CompanyUser> list = CompanyUserManager.getInstance().getList("mobile_phone = '" + mobilePhone + "' and name = '" + name + "' and deliver_area_id = " + deliverAreaId);
        if (id == null) {
            Company theCompany = CompanyManager.getInstance().getEntity(companyId);
            if (!CollectionUtils.isEmpty(list)) {
                this.writeErrorResponse(response, "该配送员已存在");
                return;
            }
            CompanyUser companyUser = new CompanyUser();
            companyUser.setCompanyId(companyId);
            companyUser.setMobilePhone(mobilePhone);
            companyUser.setName(name);
            companyUser.setDeliverAreaId(deliverAreaId);
            // 生成编号
            companyUser.setEmployeeNo(CompanyManager.getInstance().createCompanyUserNo(theCompany));
            // 密码加密
            companyUser.setPassword(EncryptManager.encryptPassword(password));
            companyUser.setCreateTime(DateHelper.toTimestamp(new Date()));
            int companyUserId = CompanyUserManager.getInstance().add(companyUser);
            // 获取配送员角色id
            List<CompanyRole> companyRoleList = CompanyRoleManager.getInstance().getList("company_id = " + companyId + " and name = '配送员'");
            if (!CollectionUtils.isEmpty(companyRoleList)) {
                int companyRoleId = companyRoleList.get(0).getId();
                CompanyUserRole companyUserRole = new CompanyUserRole();
                companyUserRole.setCompanyUserId(companyUserId);
                companyUserRole.setCompanyRoleId(companyRoleId);
                CompanyUserRoleManager.getInstance().add(companyUserRole);
            } else {
                this.writeErrorResponse(response, "当前门店没有配送员权限，请联系管理员");
            }
            this.writeSuccessResponse(response, "配送员创建成功");
        } else {
            CompanyUser companyUser = CompanyUserManager.getInstance().getEntity(id);
            if (!CollectionUtils.isEmpty(list)) {
                if (companyUser.getId() != list.get(0).getId()) {
                    this.writeErrorResponse(response, "该配送员已存在");
                    return;
                }
            }
            companyUser.setMobilePhone(mobilePhone);
            if (!companyUser.getPassword().equals(password)) {
                companyUser.setPassword(EncryptManager.encryptPassword(password));
            }
            companyUser.setName(name);
            companyUser.setDeliverAreaId(deliverAreaId);
            companyUser.setUpdateTime(DateHelper.toTimestamp(new Date()));
            CompanyUserManager.getInstance().update(companyUser);
            this.writeSuccessResponse(response, "配送员修改成功");
        }
    }

    /**
     * 生成编号
     * 规则 门店id+顺序编号
     *
     * @param companyId
     * @return
     */
    public static String getDeliverNo(Integer companyId) throws Exception {
        // 获取当前门店配送员数量
        int num = CompanyUserManager.getInstance().getList("company_id = " + companyId).size();
        if (num == 0) {
            num = 1;
        } else {
            num++;
        }
        return String.format("%s%s", companyId, num);
    }
}
