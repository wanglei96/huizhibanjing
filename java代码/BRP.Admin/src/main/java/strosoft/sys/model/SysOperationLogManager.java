package strosoft.sys.model;

import strosoft.app.common.MyBatisManager;
import strosoft.sys.data.SysOperationLogMaster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SysOperationLogManager extends SysOperationLogMaster
{
    private static SysOperationLogManager _instance;
    private SysOperationLogManager()    
    {
        this.setTableName("sys_operation_log");
        this.setTypeName("SysOperationLog");
    }
    public  static SysOperationLogManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new SysOperationLogManager();
        }
        return _instance;
    }

    public ArrayList<LinkedHashMap<String, Object>> getSysOperationLogInfoList(Integer pageIndex, Integer pageSize, String orderBy, String sqlWhere) throws Exception {
        String sqlLimit = "";
        if (pageSize > 0) {
            sqlLimit = " limit " + String.valueOf(pageIndex * pageSize) + "," + pageSize;
        }
        String sqlOrderBy = "";
        if (orderBy != null && !orderBy.equals("")) {
            sqlOrderBy = " order by  " + orderBy;
        }
        String sql = String.format("select  sol.id,\n" +
                "\t\t\t\tsol.operation_target_code as operationTargetCode,\n" +
                "\t\t\t\tsol.operation_type_code as operationTypeCode,\n" +
                "\t\t\t\tsol.operator_id as operatorId,\n" +
                "\t\t\t\tsol.operate_time as operateTime,\n" +
                "\t\t\t\tsol.primary_key as primaryKey,\n" +
                "\t\t\t\tsys_operation_target.name AS operationTargetName,\n" +
                "\t\t\t\tsys_operation_type.name AS operationTypeName,\n" +
                "\t\t\t\tsys_user.true_name AS sysUserTrueName,\n" +
                "\t\t\t\tmember.name AS memberName,\n" +
                "\t\t\t\tcompany_user.name AS companyUserName,\n" +
                "\t\t\t\tcompany_user.login_name AS companyUserLoginName,\n" +
                "\t\t\t\tsys_user.login_name AS sysUserLoginName,\n" +
                "\t\t\t\tmember.mobile_phone AS memberMobilePhone \n" +
                "from (SELECT * from sys_operation_log %s %s %s) as sol\n" +
                "LEFT JOIN \n" +
                "\t\tsys_operation_target ON sol.operation_target_code = sys_operation_target.code \n" +
                "LEFT JOIN \n" +
                "\t\tsys_operation_type ON sol.operation_type_code = sys_operation_type.code \n" +
                "LEFT JOIN \n" +
                "\t\tsys_user ON sys_user.id = sol.operator_id \n" +
                "LEFT JOIN \n" +
                "\t\tmember ON member.id = sol.operator_id \n" +
                "LEFT JOIN \n" +
                "\t\tcompany_user ON company_user.id = sol.operator_id", sqlWhere, sqlOrderBy, sqlLimit);
        return MyBatisManager.getInstance().executeHashMapList(sql);
    }
}