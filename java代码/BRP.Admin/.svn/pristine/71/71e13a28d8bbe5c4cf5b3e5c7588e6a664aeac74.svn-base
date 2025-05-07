package BRP.service;

import BRP.data.*;
import BRP.model.*;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.util.JsonHelper;
import strosoft.app.util.StringHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddRetailOrderServiceHandler extends AddServiceHandler {


    protected DataEntity processDataEntity(ServiceContext newAddServiceContext, DataEntity newDataEntity)
            throws Exception {
        HttpServletRequest request = newAddServiceContext.getRequest();
        JSONObject jData = this.getRequestData(request);
        String name = JsonHelper.getString(jData, "name");
        RetailOrder newRetailOrder = (RetailOrder) newDataEntity;
        newRetailOrder.setName(name);
        SqlSession session = newAddServiceContext.getSession();
        Member theMember = MemberManager.getInstance().getEntity(session, newRetailOrder.getMemberId());
        if(!theMember.getCompanyId().equals(newRetailOrder.getCompanyId())){
            throw new Exception("不能参与其它门店活动！");
        }
        if (newRetailOrder.getClubActivityId() != null) {
            //获取俱乐部
            ClubActivity theClubActivity = ClubActivityManager.getInstance().getEntity(session, newRetailOrder.getClubActivityId());
            if (theClubActivity.getMaxApplyQuantity() != null) {
                //获取人数
                String coditionCount = String.format("club_activity_id = %d", theClubActivity.getId());
                Integer count = ClubActivityMemberManager.getInstance().getCount(session, coditionCount);
                if (count >= theClubActivity.getMaxApplyQuantity()) {
                    throw new Exception("报名人数已满");
                }

            }
        }
        if (newRetailOrder.getNightStoryTypeId() != null) {
            NightStoryType theNightStoryType = NightStoryTypeManager.getInstance().getEntity(session, newRetailOrder.getNightStoryTypeId());
            if (theNightStoryType != null && !StringHelper.isNullOrEmpty(theNightStoryType.getCode())) {
                newRetailOrder.setNightStoryDays(theNightStoryType.getDayCount());
                if ("SubscriptionOneYear".equals(theNightStoryType.getCode())) {
                    Integer count = ViewRetailOrderManager.getInstance().
                            getCountByNightStoryType(session, theNightStoryType.getCode(), newRetailOrder.getMemberId());
                    if (count > 0) {
                        throw new Exception("已经订阅了");
                    }
                }
                if ("TryThreeDays".equals(theNightStoryType.getCode())) {
                    Integer count = ViewRetailOrderManager.getInstance().
                            getCountByNightStoryType(session, theNightStoryType.getCode(), newRetailOrder.getMemberId());
                    if (count >= (theNightStoryType.getMaxSubscribeCount() == null ? 1 : theNightStoryType.getMaxSubscribeCount())) {
                        throw new Exception(String.format("只允许免费试看%s次", theNightStoryType.getMaxSubscribeCount()));
                    }
                    newRetailOrder.setRetailOrderStatusCode("Paid");
                    //修改会员晚安故事到期时间
                    String sql = String.format("UPDATE member\n" +
                            "SET night_story_expire_date = CASE\n" +
                            "    WHEN night_story_expire_date IS NULL OR night_story_expire_date < NOW() THEN DATE_ADD(NOW(), INTERVAL %s DAY)\n" +
                            "    ELSE DATE_ADD(night_story_expire_date, INTERVAL %s DAY)\n" +
                            "END\n" +
                            "WHERE id = %d;", newRetailOrder.getNightStoryDays(), newRetailOrder.getNightStoryDays(), newRetailOrder.getMemberId());
                    MyBatisManager.getInstance().executeUpdate(session, sql);
                }
                if ("SubscribeTwentyOneDays".equals(theNightStoryType.getCode())) {
                    Integer count = ViewRetailOrderManager.getInstance().
                            getCountByNightStoryType(session, theNightStoryType.getCode(), newRetailOrder.getMemberId());
                    if (count >= (theNightStoryType.getMaxSubscribeCount() == null ? 1 : theNightStoryType.getMaxSubscribeCount())) {
                        throw new Exception(String.format("只允许免费试看%s次", theNightStoryType.getMaxSubscribeCount()));
                    }
                }
                if ("BuyOneVideo".equals(theNightStoryType.getCode())) {
                    Integer count = ViewRetailOrderManager.getInstance().
                            getCountByNightStoryVideoId(session, newRetailOrder.getStoryVideoId(), newRetailOrder.getMemberId());
                    if (count > 0) {
                        throw new Exception("该视频已经购买了");
                    }
                }
            }
        }
        return newRetailOrder;
    }
}
