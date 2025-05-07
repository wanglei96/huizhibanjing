package BRP.service;

import BRP.data.CompanySetting;
import BRP.model.CompanySettingManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UpdateCompanySettingInfoServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
        JSONObject requestData = getRequestData(request);
        List<CompanySetting> companySettingInfoList = JsonHelper.getJavaList(CompanySetting.class, requestData, "companySettingInfoList");
        if (!companySettingInfoList.isEmpty()) {
            SqlSession sqlSession = MyBatisManager.getInstance().openSession();
            try {
                for (CompanySetting companySettingInfo : companySettingInfoList) {
                    CompanySettingManager.getInstance().updateByCompanyAndCode(sqlSession, companySettingInfo.getCompanyId(), companySettingInfo.getSysSettingCode(),companySettingInfo.getValue());
                }
                MyBatisManager.getInstance().commitSession(sqlSession);
                writeSuccessResponse(response);
            } catch (Exception e) {
                MyBatisManager.getInstance().rollbackSession(sqlSession);
                throw new RuntimeException(e);
            } finally {
                MyBatisManager.getInstance().closeSession(sqlSession);
            }
        }
    }
}
