package BRP.model;

import BRP.data.ViewBookSkuAgeGroup;
import BRP.data.ViewBookSkuAgeGroupMaster;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ViewBookSkuAgeGroupManager extends ViewBookSkuAgeGroupMaster {
    private static ViewBookSkuAgeGroupManager _instance;

    public static ViewBookSkuAgeGroupManager getInstance() {
        if (_instance == null) {
            _instance = new ViewBookSkuAgeGroupManager();
        }
        return _instance;
    }

    private ViewBookSkuAgeGroupManager() {
        this.setTypeName("ViewBookSkuAgeGroup");
    }

    /**
     * 获取年龄段下所有SKU
     *
     * @param session
     * @param ageGroupId
     * @param languageCode
     * @param companyId
     * @return
     */
    public List<ViewBookSkuAgeGroup> getListByAgeGroupId(SqlSession session, Integer ageGroupId, String languageCode, Integer companyId) {
        String condition = "age_group_id = " + ageGroupId + " and language_code = '" + languageCode + "' and company_id = " + companyId;
        return getList(session, condition);
    }
}