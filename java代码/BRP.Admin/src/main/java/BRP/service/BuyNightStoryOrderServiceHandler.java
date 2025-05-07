package BRP.service;

import BRP.data.NightStoryOrder;
import BRP.model.NightStoryOrderManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.DateHelper;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class BuyNightStoryOrderServiceHandler extends ServiceHandler {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject requestData = getRequestData(request);
        String nightStoryTypeCode = JsonHelper.getString(requestData, "nightStoryTypeCode");
        String subscribeTypeCode = JsonHelper.getString(requestData, "subscribeTypeCode");
        Integer memberId = JsonHelper.getInt(requestData, "memberId");

        SqlSession sqlSession = MyBatisManager.getInstance().openSession();

        try {
            //查询没过期的晚安故事订阅记录
            String getNightStoryOrderCondition = "member_id=" + memberId + " and expire_time>=NOW()";
            List<NightStoryOrder> list = NightStoryOrderManager.getInstance().getList(sqlSession, getNightStoryOrderCondition);

            //判断是否还有没过期的订阅
            if (list.isEmpty()) {
                String getNightStoryTypeSql = "SELECT * from night_story_type where code = '" + nightStoryTypeCode + "'";
                ArrayList<LinkedHashMap<String, Object>> nightStoryType = MyBatisManager.getInstance().executeHashMapList(sqlSession, getNightStoryTypeSql);
                LinkedHashMap<String, Object> stringObjectLinkedHashMap = nightStoryType.get(0);
                Integer dayCount = (Integer) stringObjectLinkedHashMap.get("day_count");
                //晚安故事订阅
                NightStoryOrder newNightStoryOrder = new NightStoryOrder();
                String todayStr = DateHelper.format(new Date(), "yyyy-MM-dd");
                Date subscribeTimeDate = DateHelper.toDate(todayStr);
                Date expireTimeDate = DateHelper.addDays(subscribeTimeDate, dayCount + 1);
                newNightStoryOrder.setSubscribeTime(DateHelper.toTimestamp(subscribeTimeDate));
                newNightStoryOrder.setExpireTime(DateHelper.toTimestamp(expireTimeDate));
                newNightStoryOrder.setMemberId(memberId);
                newNightStoryOrder.setSubscribeTypeCode(subscribeTypeCode);

                NightStoryOrderManager.getInstance().add(sqlSession, newNightStoryOrder);
                MyBatisManager.getInstance().commitSession(sqlSession);
                writeSuccessResponse(response);
            } else {
                throw new Exception("已存在订阅");
            }
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }

    }
}
