package BRP.service;

import BRP.data.Company;
import BRP.model.CompanyManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SetDefaultCompanyServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
        JSONObject jData = getRequestData(request);
        Integer companyId = JsonHelper.getInt(jData, "companyId");
        if (companyId == null) {
            throw new Exception("companyId不能为空");
        }
        MyBatisManager.getInstance().executeUpdate("update company set is_default=(id=" + companyId + ")");
        writeSuccessResponse(response);
    }
}
