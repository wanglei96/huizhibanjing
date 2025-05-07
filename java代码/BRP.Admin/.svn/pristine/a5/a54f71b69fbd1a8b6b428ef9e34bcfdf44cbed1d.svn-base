package BRP.model;

import BRP.data.ViewRetailOrder;
import BRP.data.ViewRetailOrderMaster;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ViewRetailOrderManager extends ViewRetailOrderMaster {
    private static ViewRetailOrderManager _instance;

    public static ViewRetailOrderManager getInstance() {
        if (_instance == null) {
            _instance = new ViewRetailOrderManager();
        }
        return _instance;
    }

    private ViewRetailOrderManager() {
        this.setTypeName("ViewRetailOrder");
    }

    /**
     * 根据订阅类型获取订单列表
     *
     * @param session
     * @param nightStoryTypeCode
     * @param memberId
     * @return
     */
    public Integer getCountByNightStoryType(SqlSession session, String nightStoryTypeCode, Integer memberId) {
        String condition = String.format("night_story_type_code = '%s' and member_id = %d and retail_order_status_code = '%s'  %s", nightStoryTypeCode, memberId,
                "Paid", nightStoryTypeCode.equals("SubscribeOneYear") ? " and DATE_ADD(pay_time, INTERVAL 365 DAY) < NOW()" : "");
        return getCount(session, condition);
    }

    public Integer getCountByNightStoryVideoId(SqlSession session, Integer storyVideoId, Integer memberId) {
        String condition = String.format("story_video_id = %d and member_id = %d and retail_order_status_code = '%s'", storyVideoId, memberId,
                "Paid");
        return getCount(session, condition);
    }
}