package BRP.model;

import BRP.data.Company;
import BRP.data.CompanyMaster;
import BRP.data.CompanyUser;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CompanyManager extends CompanyMaster {
    private static CompanyManager _instance;

    private CompanyManager() {
        this.setTableName("company");
        this.setTypeName("Company");
    }

    public static CompanyManager getInstance() {
        if (_instance == null) {
            _instance = new CompanyManager();
        }
        return _instance;
    }

    /**
     * 获取默认门店
     *
     * @param sqlSession
     * @return
     */
    public Company getDefaultCompanyInfo(SqlSession sqlSession) {
        List<Company> lstCompany = this.getList(sqlSession, "is_default is true");
        if (lstCompany.size() > 0) {
            return lstCompany.get(0);
        }
        return null;
    }

    /**
     * 创建门店用户编号
     *
     * @param theCompany
     * @return
     */
    public String createCompanyUserNo(Company theCompany) throws Exception {
        //获取门店下最大编号
        String condition = "company_id = " + theCompany.getId();
        List<CompanyUser> lstCompanyUser = CompanyUserManager.getInstance().getList(condition, "id desc");
        if (lstCompanyUser.size() > 0) {
            // 获取公司编号
            String companyNo = theCompany.getCompanyNo();

            // 获取员工号
            String employeeNo = lstCompanyUser.get(0).getEmployeeNo();

            // 去掉公司编号前缀
            String employeeNoWithoutPrefix = employeeNo.replace(companyNo, "");

            // 将员工号部分转换为整数并自增
            int incrementedEmployeeNo = Integer.parseInt(employeeNoWithoutPrefix) + 1;

            // 使用字符串格式化确保自增后的员工号部分保持三位数形式
            String formattedEmployeeNo = String.format("%03d", incrementedEmployeeNo);

            // 构造新的员工编号
            String newEmployeeNo = companyNo + formattedEmployeeNo;

            return newEmployeeNo;
        }
        return theCompany.getCompanyNo() + "001";
    }
}