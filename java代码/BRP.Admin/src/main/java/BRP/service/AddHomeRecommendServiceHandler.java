package BRP.service;

import BRP.data.HomeNewBook;
import BRP.data.HomeRecommend;
import BRP.model.HomeNewBookManager;
import BRP.model.HomeRecommendManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.JsonHelper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AddHomeRecommendServiceHandler extends AddServiceHandler {
    /**
     * 处理数据实体
     *
     * @param request
     * @param response
     * @param newDataEntity
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        JSONObject requestData = getRequestData(newAddServiceContext.getRequest());
        Integer companyId = JsonHelper.getInt(requestData, "companyId");

        HomeRecommend newHomeRecommend = (HomeRecommend) newDataEntity;
        MySqlConditionBuilder mb = new MySqlConditionBuilder();
        mb.addEqualCondition("company_id", companyId);
        mb.addEqualCondition("club_activity_id", newHomeRecommend.getClubActivityId());
        List<HomeRecommend> listByClubActivityId = HomeRecommendManager.getInstance().getList(newAddServiceContext.session, mb.toString());
        if (!listByClubActivityId.isEmpty()) {
            throw new Exception("不能重复添加该俱乐部活动");
        }

        return newDataEntity;
    }
}
