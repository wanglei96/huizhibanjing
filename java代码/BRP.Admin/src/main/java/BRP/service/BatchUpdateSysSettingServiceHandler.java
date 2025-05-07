package BRP.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysSetting;
import strosoft.sys.model.SysSettingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchUpdateSysSettingServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, Exception {
        JSONObject requestData = getRequestData(request);
        List<SysSetting> sysSettingList = JsonHelper.getJavaList(SysSetting.class, requestData, "sysSettingList");

        if (!sysSettingList.isEmpty()) {
            SysSettingManager.getInstance().update(sysSettingList);
        }
        writeSuccessResponse(response);
    }
}
