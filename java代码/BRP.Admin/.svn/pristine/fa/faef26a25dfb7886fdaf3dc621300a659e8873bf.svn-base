package BRP.service;

import BRP.data.ViewMember;
import BRP.model.ViewMemberManager;
import net.sf.json.JSONObject;
import strosoft.app.common.MyBatisManager;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GetMemberPermissionServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jData = this.getRequestData(request);
        Integer memberId = jData.getInt("memberId");
        String sql = String.format("SELECT\n" +
                "    CASE\n" +
                "        WHEN vm.night_story_expire_date > NOW() OR EXISTS (SELECT 1 FROM retail_order WHERE member_id = vm.id AND story_video_id IS NOT NULL AND retail_order_status_code='Paid') THEN TRUE\n" +
                "        ELSE FALSE\n" +
                "    END AS is_night_story,\n" +
                "    CASE\n" +
                "        WHEN vm.member_card_card_no IS NOT NULL AND vm.member_card_expire_date > NOW() AND is_cancel is not true  THEN TRUE\n" +
                "        ELSE FALSE\n" +
                "    END AS is_borrow_book,\n" +
                "    CASE\n" +
                "        WHEN EXISTS (SELECT 1 FROM club_activity_member WHERE member_id = vm.id) THEN TRUE\n" +
                "        ELSE FALSE\n" +
                "    END AS is_club_activity,\n" +
                "    CASE\n" +
                "        WHEN vm.is_activated = true THEN TRUE\n" +
                "        ELSE FALSE\n" +
                "    END AS is_delivery\n" +
                "FROM view_member vm\n" +
                "WHERE vm.id = %d;", memberId);
        ArrayList<LinkedHashMap<String, Object>> alData = MyBatisManager.getInstance().executeHashMapList(sql);
        LinkedHashMap<String, Object> lhmData = alData.get(0);
        JSONObject jResult = JsonHelper.toJSONObject(lhmData);
        this.writeSuccessResponse(response, jResult);
    }

}
