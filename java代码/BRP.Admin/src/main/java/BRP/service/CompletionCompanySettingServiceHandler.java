package BRP.service;

import BRP.model.CompanySettingManager;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CompletionCompanySettingServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            //补充所有门店系统参数
            CompanySettingManager.getInstance().completionCompanySetting(sqlSession);
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
