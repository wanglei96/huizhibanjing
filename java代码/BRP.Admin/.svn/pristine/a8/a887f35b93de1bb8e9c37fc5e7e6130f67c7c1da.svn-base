package strosoft.app.service;

import BRP.model.CompanySettingManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.util.JsonHelper;
import strosoft.sys.data.SysSetting;
import strosoft.sys.model.SysSettingManager;

import java.io.IOException;

public class AddSysSettingServiceHandler extends AddServiceHandler {
    @Override
    protected void postProcess(ServiceContext newAddServiceContext) throws Exception {
        //补充所有门店系统参数
        CompanySettingManager.getInstance().completionCompanySetting(newAddServiceContext.session);
    }
}
