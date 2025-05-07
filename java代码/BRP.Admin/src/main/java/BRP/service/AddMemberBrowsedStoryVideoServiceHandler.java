package BRP.service;

import BRP.data.MemberBrowsedStoryVideo;
import BRP.model.MemberBrowsedStoryVideoManager;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import strosoft.app.common.MyBatisManager;
import strosoft.app.common.MySqlConditionBuilder;
import strosoft.app.data.DataEntity;
import strosoft.app.service.AddServiceHandler;
import strosoft.app.service.ServiceContext;
import strosoft.app.service.ServiceHandler;
import strosoft.app.util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AddMemberBrowsedStoryVideoServiceHandler extends ServiceHandler {
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject requestData = getRequestData(request);
        Integer memberId = JsonHelper.getInt(requestData, "memberId");
        Integer storyVideoId = JsonHelper.getInt(requestData, "storyVideoId");
        SqlSession sqlSession = MyBatisManager.getInstance().openSession();
        try {
            MySqlConditionBuilder mb = new MySqlConditionBuilder();
            mb.addEqualCondition("member_id", memberId);
            mb.addEqualCondition("story_video_id", storyVideoId);
            List<MemberBrowsedStoryVideo> list = MemberBrowsedStoryVideoManager.getInstance().getList(sqlSession, mb.toString());

            if (list.isEmpty()) {
                MemberBrowsedStoryVideo newMemberBrowsedStoryVideo = new MemberBrowsedStoryVideo();
                newMemberBrowsedStoryVideo.setMemberId(memberId);
                newMemberBrowsedStoryVideo.setStoryVideoId(storyVideoId);
                MemberBrowsedStoryVideoManager.getInstance().add(sqlSession, newMemberBrowsedStoryVideo);
            }
            MyBatisManager.getInstance().commitSession(sqlSession);
        } catch (Exception e) {
            MyBatisManager.getInstance().rollbackSession(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MyBatisManager.getInstance().closeSession(sqlSession);
        }
        this.writeSuccessResponse(response);
    }
}
