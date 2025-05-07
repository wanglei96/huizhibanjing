package BRP.model;
import BRP.data.ViewAgeGroupBookTag;
import BRP.data.ViewAgeGroupBookTagMaster;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ViewAgeGroupBookTagManager extends ViewAgeGroupBookTagMaster
{
    private static ViewAgeGroupBookTagManager _instance;
    public  static ViewAgeGroupBookTagManager getInstance()    
    {
        if (_instance == null)         
        {
            _instance = new ViewAgeGroupBookTagManager();
        }
        return _instance;
    }
    private   ViewAgeGroupBookTagManager()    
    {
        this.setTypeName("ViewAgeGroupBookTag");
    }

    /**
     * 获取年级标签
     * @param session
     * @param ageGroupId
     * @param languageCode
     * @param companyId
     * @return
     */
    public List<ViewAgeGroupBookTag> getListByAgeGroupId(SqlSession session, Integer ageGroupId, String languageCode, Integer companyId) {
        String condition = " age_group_id = " + ageGroupId + " and language_code = '" + languageCode + "' and company_id = " + companyId;
        return getList(session, condition);
    }
}